# Dictionary Learning Platform

Spring Boot web app for vocabulary learning (English ↔ Slovak), single Gradle module (`app`) under a
multi-project build with a `buildSrc` convention-plugin setup.

## Build, test, lint

Run all commands from the repo root using the Gradle wrapper (`./gradlew`, or `gradlew.bat` on Windows).

- Build: `./gradlew :app:build`
- Run all tests: `./gradlew :app:test`
- Run a single test class: `./gradlew :app:test --tests "com.dictionary.learning.platform.word.WordServiceTest"`
- Run a single test method: `./gradlew :app:test --tests "com.dictionary.learning.platform.word.WordServiceTest.methodName"`
- Format/check style (Spotless, uses Palantir Java Format): `./gradlew spotlessCheck` / `./gradlew spotlessApply`
- Static migrations (OpenRewrite, active recipes defined in `buildSrc`): `./gradlew rewriteRun` (applies) / `./gradlew rewriteDryRun`
- Run app locally with dev profile: `./gradlew :app:bootTestRun` (activates `spring.profiles.active=dev`, wired in `app/build.gradle.kts`)
- CI runs only `./gradlew :app:test` (see `.github/workflows/01-run-tests.yml`) on Java 25 (Zulu).

Tests require **Docker** — repository-layer tests use Testcontainers (`BaseTestRepository`) to spin up a real
`postgres` container (`@Testcontainers(disabledWithoutDocker = true)`), so they are skipped without Docker.
The `test` Gradle task always runs with `-Dspring.profiles.active=dev`.

## Architecture

Single Spring Boot module (`app`), package root `com.dictionary.learning.platform`, organized by **feature**, not by
technical layer:
- `lesson/`, `word/`, `user/` — each is a vertical slice containing its own entity, repository, service, DTOs, and
  a `*NotFound` exception (e.g. `LessonNotFound`, `WordNotFound`).
- `ui/` — Spring MVC controllers and view-model glue for JTE templates (`app/src/main/jte/`).
- `config/` — `SecurityConfig` (form login, RBAC, CSRF, session policy) and `AppConfig`.
- `utils/` — cross-cutting helpers (e.g. `DictionaryUtils` for answer normalization/success-rate math).

Data model: `User —1:N→ Lesson —1:N→ Word`. Schema is Flyway-managed (`app/src/main/resources/db/migration`,
`V001__INIT_DATABASE.sql`); `spring.jpa.hibernate.ddl-auto=none` — **never** rely on Hibernate auto-DDL, always add a
new Flyway migration for schema changes.

Views use **JTE** templates (`app/src/main/jte`), precompiled in production (`jte.generate()`,
`binaryStaticContent=true` in `app/build.gradle.kts`); JTE dev mode is disabled so template changes need a rebuild
unless running with the dev profile.

Security: form-based login at `/login`, `@PreAuthorize`-guarded admin endpoints (role `ADMIN`), CSRF enabled with
per-form tokens, one concurrent session per user, `DelegatingPasswordEncoder` for password hashing.

## Conventions

- **Domain-driven package-by-feature** layout — put new code in the relevant feature package (`lesson`, `word`,
  `user`) rather than generic `controller`/`service`/`repository` layers.
- Success-rate math (session rate = correct/attempts, lesson rate = weighted average `(prev + new*2)/3`) lives in
  `utils/DictionaryUtils` — reuse it instead of reimplementing rounding/weighting logic; results use `HALF_UP`
  rounding to 3 decimal places.
- Answer validation normalizes case, whitespace, and strips `. , ! ?` before comparing — keep any related changes
  consistent with this normalization in `DictionaryUtils`.
- Not-found errors use dedicated per-feature exceptions (`LessonNotFound`, `WordNotFound`), not generic exceptions.
- Repository integration tests extend `BaseTestRepository` (shared Testcontainers Postgres) and use
  `@DataJpaTest(properties = "spring.flyway.locations=classpath:/db/migration/postgresql")` with
  `@AutoConfigureTestDatabase(replace = NONE)` so Flyway migrations run against the real container instead of an
  embedded DB.
- Code style is enforced by Spotless with Palantir Java Format + import ordering — run `spotlessApply` before
  committing rather than hand-formatting.
- Java toolchain is pinned to 25 (`java-library-conventions.gradle.kts`); Gradle build logic and shared config live
  in `buildSrc`, not inline in `app/build.gradle.kts`.
