@import com.dictionary.learning.platform.lesson.LessonDto
@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<LessonDto> lessons
@param List<Integer> pageNumbers

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Manage lessons</h2>
        <div class="relative overflow-x-auto">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">
                        Title
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Grade
                    </th>
                    <th scope="col" class="px-6 py-3">
                        For user
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Success rate
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Manage words
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Update lesson
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Remove lesson
                    </th>
                </tr>
                </thead>
                <tbody>
                @for(LessonDto lesson: lessons)
                    <tr class="bg-white border-b">
                        <th scope="row" class="px-6 py-4 font-medium whitespace-nowrap text-gray-900">
                            ${lesson.title()}
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium whitespace-nowrap text-gray-900">
                            ${lesson.grade()}
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium whitespace-nowrap text-gray-900">
                            ${lesson.user().getUsername()}
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium whitespace-nowrap
                        @if(lesson.successRate() < 0.5)
                            text-red-500
                        @elseif(lesson.successRate() < 0.9)
                            text-yellow-600
                        @else
                            text-green-700
                        @endif
                        ">${lesson.successRate() * 100}%
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            <a href="/manage-words?lessonId=${lesson.id()}"
                               class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                                Words
                            </a>
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            <a href="/existing-lesson?lessonId=${lesson.id()}"
                               class="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 border border-transparent">
                                Update
                            </a>
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            <a href="/delete-lesson?lessonId=${lesson.id()}"
                               onclick="if (!(confirm('Are you sure you want to delete this lesson?'))) return false"
                               class="px-4 py-2 bg-red-500 text-white rounded-md hover:bg-red-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 border border-transparent">
                                Remove
                            </a>
                        </th>
                    </tr>
                @endfor
                </tbody>
            </table>
            @if(pageNumbers.size() > 1)
                <div class="flex flex-col items-center pt-6">
                    <nav aria-label="Page navigation">
                        <ul class="inline-flex -space-x-px text-sm">
                            @for(int pageNumber : pageNumbers)
                                <li>
                                    <a class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700
                                @if(pageNumber == pageNumbers.getFirst())
                                    rounded-l-lg
                                @elseif(pageNumber == pageNumbers.getLast())
                                    rounded-r-lg
                                 @endif
                                "
                                       href="/lesson-selection?page=${pageNumber - 1}">
                                        ${pageNumber}
                                    </a>
                                </li>
                            @endfor
                        </ul>
                    </nav>
                </div>
            @endif
        </div>
    </div>
`)
