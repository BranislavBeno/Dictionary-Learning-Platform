@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import com.dictionary.learning.platform.word.WordDto
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<WordDto> words
@param List<Integer> pageNumbers
@param long lessonId
@param String lesson
@param int grade
@param String forUser

@template.layout.main(
pageTitle = "Manage words",
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Manage words</h2>
        <div class="flex flex-col items-end pb-6">
            <a href="/new-word"
               class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                Add word...
            </a>
        </div>
        <div class="grid gap-6 mb-6 md:grid-cols-3">
            <div>
                <label for="lesson" class="block mb-2 text-sm font-medium text-gray-900">Lesson</label>
                <p id="lesson"
                   class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm bg-gray-200">
                    ${lesson}
                </p>
            </div>
            <div>
                <label for="grade" class="block mb-2 text-sm font-medium text-gray-900">Grade</label>
                <p id="grade"
                   class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm bg-gray-200">
                    ${grade}
                </p>
            </div>
            <div>
                <label for="forUser" class="block mb-2 text-sm font-medium text-gray-900">For user</label>
                <p id="forUser"
                   class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm bg-gray-200">
                    ${forUser}
                </p>
            </div>
        </div>
        <div class="relative overflow-x-auto">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50">
                <tr>
                    <th scope="col" class="px-6 py-3">
                        In english
                    </th>
                    <th scope="col" class="px-6 py-3">
                        In slovak
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Update word
                    </th>
                    <th scope="col" class="px-6 py-3">
                        Remove word
                    </th>
                </tr>
                </thead>
                <tbody>
                @for(WordDto word: words)
                    <tr class="bg-white border-b">
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            ${word.en()}
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            ${word.sk()}
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            <a href="/existing-word?wordId=${word.id()}"
                               class="px-4 py-2 bg-green-500 text-white rounded-md hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500 border border-transparent">
                                Update
                            </a>
                        </th>
                        <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap">
                            <a href="/delete-word?wordId=${word.id()}"
                               onclick="if (!(confirm('Are you sure you want to delete this word?'))) return false"
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
                                       href="/manage-words?lessonId=${lessonId}&page=${pageNumber - 1}">
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