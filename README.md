[![Application Tests](https://github.com/BranislavBeno/Dictionary-Learning-Platform/actions/workflows/01-run-tests.yml/badge.svg)](https://github.com/BranislavBeno/Dictionary-Learning-Platform/actions/workflows/01-run-tests.yml)
[![Application Deployment](https://github.com/BranislavBeno/Dictionary-Learning-Platform/actions/workflows/02-deploy-docker-image.yml/badge.svg)](https://github.com/BranislavBeno/Dictionary-Learning-Platform/actions/workflows/02-deploy-docker-image.yml)  
[![](https://img.shields.io/badge/Java-25-blue)](/app/build.gradle.kts)
[![](https://img.shields.io/badge/Spring%20Boot-4.0.2-blue)](/app/build.gradle.kts)
[![](https://img.shields.io/badge/Testcontainers-2.0.3-blue)](/app/build.gradle.kts)
[![](https://img.shields.io/badge/Gradle-9.3.1-blue)](/gradle/wrapper/gradle-wrapper.properties)
[![](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

# Dictionary Learning Platform - Simple web application for vocabulary learning

## Executive Summary

The Dictionary Learning Platform is a web-based vocabulary learning application.  
It provides an interactive environment for students to learn and practice vocabulary through lessons,
with administrative capabilities for managing users, lessons, and words.  
The platform tracks learning progress through success rates and supports bidirectional language learning (currently English ↔ Slovak).

## User Workflows

### Standard User Workflow

1. **Login**
    - Navigate to `/login`
    - Enter credentials
    - System validates and creates session
    - Redirect to home page

2. **Select Lesson**
    - View personal lessons list (paginated)
    - Review lesson titles, grades, and success rates
    - Click on desired lesson

3. **Choose Learning Direction**
    - Select `English → Slovak` or `Slovak → English`

4. **Practice Session**
    - View question in source language
    - Type answer in target language
    - Submit answer
    - Receive immediate feedback
    - Proceed to next word
    - Repeat until lesson complete

5. **View Results**
    - Review session success rate
    - System updates overall lesson statistics
    - Return to lesson selection or home

### Administrator Workflow

1. **Login as Admin**
    - Access full administration interface
    - View global statistics

2. **Manage Lessons**
    - Navigate to lesson administration
    - Create new lesson (assign title, grade, user)
    - Edit existing lessons
    - Delete obsolete lessons
    - View cross-user lesson data

3. **Manage Words**
    - Select lesson context
    - Add vocabulary pairs (English/Slovak)
    - Edit existing translations
    - Remove incorrect or duplicate entries
    - Review word-level statistics

4. **User Management**
    - View all system users
    - Assign lessons to specific users
    - Monitor user progress and grades

## Technical Architecture

### Technology Stack

- **Backend Framework**: Spring Boot
- **Programming Language**: Java
- **Build Tool**: Gradle
- **Template Engine**: JTE (Java Template Engine)
- **Database**: PostgreSQL
- **Migration Tool**: Flyway
- **Security**: Spring Security
- **Testing**: Testcontainers, JUnit
- **Containerization**: Docker (Alpine-based with Azul Zulu OpenJDK)
- **Deployment**: DigitalOcean App Platform

### Architecture Components

1. **Multi-layer Architecture**
    - Presentation Layer (Controllers & JTE Templates)
    - Service Layer (Business Logic)
    - Repository Layer (Data Access with Spring Data JPA)
    - Domain Layer (Entities)

2. **Security Layer**
    - Form-based authentication
    - Role-based access control (ADMIN, USER)
    - CSRF protection
    - Password encryption using DelegatingPasswordEncoder
    - Session management with maximum 1 concurrent session

3. **Database Schema**
    - Users table (authentication & profile)
    - Lessons table (vocabulary lesson metadata)
    - Words table (vocabulary entries)
    - Relationships: User → Lessons (one-to-many), Lesson → Words (one-to-many)

## Core Features

### 1. User Management

#### User Roles
- **ADMIN**: Full system access including user, lesson, and word management
- **USER**: Access to personal lessons and learning functionality

#### User Attributes
- Username (unique identifier)
- Encrypted password
- Role assignment
- Grade level (educational context)
- Associated lessons

#### Authentication & Authorization
- Custom user details service integrating with database
- Session-based authentication
- Secure password storage
- Login/logout functionality
- Error handling for invalid credentials

### 2. Lesson Management

#### Lesson Structure
Each lesson contains:
- **Title**: Descriptive name
- **Grade**: Educational level/difficulty
- **Success Rate**: Aggregate performance metric (0-100% scale)
- **Owner**: Associated user
- **Word Collection**: Set of vocabulary pairs

#### Lesson Operations

**For Users:**
- Browse personal lessons (paginated view)
- Select lesson for practice
- View lesson statistics and success rates
- Track learning progress over time

**For Administrators:**
- Create new lessons
- Edit existing lessons (title, grade, user assignment)
- Delete lessons (cascade deletion of associated words)
- Assign lessons to specific users
- View all lessons across all users
- Paginated lesson browsing

#### Success Rate Calculation
- Calculated after each lesson completion
- Weighted average formula: `(previousRate + newRate × 2) / 3`
- Emphasizes recent performance while considering history
- Precision: 3 decimal places with HALF_UP rounding

### 3. Word Management (Vocabulary)

#### Word Structure
Each word entry consists of:
- **English Translation** (en)
- **Slovak Translation** (sk)
- **Success Rate**: Individual word performance metric
- **Parent Lesson**: Association with specific lesson

#### Word Operations

**For Administrators:**
- Add new words to lessons
- Edit existing word translations
- Delete words from lessons
- Bulk word management within lesson context
- Paginated word display

#### Validation
- Both English and Slovak fields are required (@NotBlank)
- Whitespace trimming on save
- Case-insensitive answer checking during practice

### 4. Interactive Learning Module

#### Lesson Selection Workflow
1. User navigates to lesson selection page
2. System displays paginated list of user's lessons
3. User selects a lesson
4. User chooses learning direction (EN→SK or SK→EN)
5. System initiates practice session

#### Practice Session Features

**Question-Answer Flow:**
- Iterative presentation of vocabulary pairs
- Question displayed based on selected language direction
- Text input for user answers
- Real-time answer validation
- Immediate feedback (correct/incorrect)
- Progress through entire word set

**Answer Validation:**
- Case-insensitive comparison
- Whitespace normalization (multiple spaces → single space)
- Punctuation removal (!, . , ?)
- Trimming of leading/trailing spaces
- Exact match requirement after normalization

**Scoring System:**
- Counter tracks attempts per word
- Initial correct answer: 1 attempt
- Additional attempts increment counter
- Success rate = `wordsCount / totalAttempts`
- Precision: 3 decimal places

#### Results & Feedback
- Post-lesson summary showing success rate
- Automatic update of lesson success rate
- Historical performance tracking
- Weighted average calculation for ongoing progress

### 5. Administration Interface

#### Access Control
- Restricted to ADMIN role via `@PreAuthorize` annotation
- Comprehensive CRUD operations for all entities

#### Administrative Capabilities

**User Administration:**
- View all registered users
- User selection for lesson assignment
- Grade management per user

**Lesson Administration:**
- Global lesson overview (all users)
- Create/edit/delete lessons
- Cross-user lesson management
- Bulk operations support
- Lesson-to-user assignment

**Word Administration:**
- Context-aware word management (within lesson scope)
- Add/edit/delete vocabulary entries
- Batch word addition to lessons
- Word-level statistics tracking

#### User Interface Features
- Paginated data display
- Breadcrumb navigation
- Contextual information display (username, grade, lesson title)
- CSRF token integration for form security

### 6. Data Persistence & Migration

#### Database Schema Management
- **Flyway Integration**: Version-controlled migrations
- **Initial Schema** (V001__INIT_DATABASE.sql):
    - User sequences starting at 10
    - Lesson sequences starting at 10
    - Word sequences starting at 10
    - Foreign key constraints ensuring referential integrity
    - Cascade operations for data consistency

#### JPA Configuration
- Hibernate DDL auto: none (Flyway-managed)
- Open-in-view: disabled (performance optimization)
- Sequence generators with allocation size 1
- Lazy loading for relationships
- Orphan removal for cascading deletes

#### Repository Layer
- Spring Data JPA repositories
- Custom query methods with projections (DTOs)
- Paginated query support
- Username-based filtering
- Lesson-based word filtering

### 7. Performance & Optimization

#### Virtual Threads
- Spring Boot virtual threads enabled
- Improved concurrency handling
- Better resource utilization

#### Pagination
- Configurable page size (default: 7 items)
- Memory-efficient data loading
- Dynamic page number generation
- Content and metadata separation

#### Template Optimization
- JTE precompiled templates in production
- Binary static content enabled
- Development mode disabled in production
- Reduced template rendering overhead

### 8. Monitoring & Operations

#### Spring Boot Actuator
- Health endpoint with detailed information
- Info endpoint for application metadata
- SBOM (Software Bill of Materials) endpoint
- Git properties integration (commit info, branch, etc.)

#### Health Checks
- Database connectivity verification
- HTTP path: `/actuator/health`
- Always show detailed health information

#### Logging
- Spring Security logging configurable (ERROR/DEBUG/INFO)
- Application-wide logging configuration
- Production-ready logging setup

### 9. Security Features

#### CSRF Protection
- Automatic CSRF token generation
- Token injection in all forms
- Custom advice for CSRF hidden inputs
- Protection against cross-site request forgery

#### Session Management
- Session creation policy: ALWAYS
- Maximum concurrent sessions: 1 per user
- Cookie deletion on logout (JSESSIONID)
- Session fixation protection

#### Password Security
- Delegating password encoder
- Support for multiple encoding algorithms
- Automatic password upgrade capability
- BCrypt default for new passwords

#### Authorization
- Method-level security with `@PreAuthorize`
- Role-based access control
- Secured administration endpoints
- Public access for login, error, and actuator/info pages

### 10. Deployment & DevOps

#### Docker Containerization
- **Multi-stage Build**:
    - Stage 1: Gradle build with source compilation
    - Stage 2: Layered JAR extraction
    - Stage 3: Lightweight JRE runtime image

- **Optimizations**:
    - Alpine Linux base (minimal footprint)
    - Azul Zulu JRE headless (reduced size)
    - Layered JAR structure (efficient caching)
    - Non-root user execution (security)
    - Dumb-init for proper signal handling

#### DigitalOcean Deployment
- **Infrastructure**:
    - PostgreSQL managed database
    - Container-based app deployment
    - Frankfurt region hosting

- **Configuration**:
    - Environment variable injection
    - Database connection via connection string
    - SSL-required PostgreSQL connection
    - Health check monitoring
    - Docker Hub registry integration

#### CI/CD Pipeline
- Automated testing workflow
- Docker image building and deployment
- GitHub Actions integration
- Continuous deployment to DigitalOcean

## Data Models

### User Entity
```
- id (BIGINT, Primary Key)
- username (VARCHAR, NOT NULL, Unique)
- password (VARCHAR, NOT NULL, Encrypted)
- role (VARCHAR)
- grade (INTEGER, NOT NULL)
- lessons (One-to-Many → Lesson)
```

### Lesson Entity
```
- id (BIGINT, Primary Key)
- title (VARCHAR)
- grade (INTEGER, NOT NULL)
- successRate (DOUBLE PRECISION)
- user_id (BIGINT, Foreign Key → User)
- words (One-to-Many → Word)
```

### Word Entity
```
- id (BIGINT, Primary Key)
- en (VARCHAR, NOT NULL)
- sk (VARCHAR, NOT NULL)
- successRate (DOUBLE PRECISION)
- lesson_id (BIGINT, Foreign Key → Lesson)
```

## API Endpoints

### Public Endpoints
- `GET /login` - Login page
- `GET /error` - Error handling
- `GET /actuator/info` - Application information

### Authenticated User Endpoints
- `GET /` - Home page
- `GET /lesson-selection` - Browse user's lessons (paginated)
- `GET /choose-lesson` - Select lesson for practice
- `POST /apply-lesson` - Start practice session
- `POST /check-word` - Validate user answer
- `POST /next-word` - Proceed to next vocabulary item
- `GET /lesson-results` - View session results

### Administrator Endpoints (ADMIN role required)
- `GET /administration` - Admin dashboard
- `GET /manage-lessons` - Lesson management interface (paginated)
- `GET /manage-words` - Word management interface (paginated)
- `GET /new-lesson` - Create lesson form
- `GET /existing-lesson` - Edit lesson form
- `POST /save-lesson` - Persist lesson changes
- `GET /delete-lesson` - Remove lesson
- `GET /new-word` - Create word form
- `GET /existing-word` - Edit word form
- `POST /save-word` - Persist word changes
- `GET /delete-word` - Remove word

## Configuration

### Application Configuration
- **Server Port**: 8080
- **Virtual Threads**: Enabled
- **JPA Open-in-View**: Disabled
- **Whitelabel Error**: Disabled
- **Page Size**: 7 items (configurable)

### Template Configuration (JTE)
- **Development Mode**: False (production)
- **Precompiled Templates**: True
- **Binary Static Content**: True

### Security Configuration
- **Login Page**: `/login`
- **Default Success URL**: `/`
- **Logout Success URL**: `/login`
- **Session Policy**: ALWAYS
- **Max Concurrent Sessions**: 1

### Database Configuration
- **Hibernate DDL**: none (Flyway-managed)
- **Connection Pool**: HikariCP (default)
- **SSL Mode**: Required (production)

## Testing Strategy

### Test Infrastructure
- **Testcontainers**: Isolated PostgreSQL instances
- **Base Test Configuration**: Shared container configuration
- **Test Profiles**: Separate dev profile with H2/PostgreSQL

### Test Coverage
- **Repository Tests**: Data access validation
- **Service Tests**: Business logic verification
- **Schema Validation**: Database integrity checks
- **Utility Tests**: Algorithm correctness (rate calculations)

### Test Categories
1. Unit Tests (Service layer)
2. Integration Tests (Repository layer with Testcontainers)
3. Schema Validation Tests
4. Utility Function Tests

## Success Rate Algorithm

### Session Success Rate
```
successRate = totalWords / totalAttempts
```
Where:
- `totalWords`: Number of unique words in lesson
- `totalAttempts`: Sum of all attempts across all words
- Precision: 3 decimal places (HALF_UP rounding)

### Lesson Historical Rate
```
newRate = (previousRate + currentSessionRate × 2) / 3
```
This weighted average:
- Gives double weight to recent performance
- Maintains historical context
- Smooths out performance fluctuations
- Encourages consistent practice

## Error Handling

### Application-Level Errors
- Custom exception: `WordNotFound`
- Custom exception: `LessonNotFound`
- Custom exception: `UsernameNotFoundException`
- Whitelabel error page disabled
- Custom error templates (JTE)

### Security Errors
- Invalid login credentials
- Unauthorized access attempts
- CSRF token validation failures
- Session timeout handling

## Future Enhancement Opportunities

1. **Multi-language Support**: Expand beyond English-Slovak
2. **Spaced Repetition**: Implement SRS algorithm for optimal retention
3. **Audio Pronunciation**: Add text-to-speech for listening practice
4. **Progress Analytics**: Enhanced statistics and visualization
5. **Mobile Application**: Native iOS/Android apps
6. **Gamification**: Points, badges, and leaderboards
7. **Collaborative Learning**: Shared lessons and peer challenges
8. **Export/Import**: Lesson data portability (CSV, JSON)
9. **API Integration**: RESTful API for third-party integrations
