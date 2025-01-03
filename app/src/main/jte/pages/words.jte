@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import com.dictionary.learning.platform.word.WordDto
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<WordDto> words
@param List<Integer> pageNumbers
@param String forUser
@param int grade
@param int lesson

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <div class="flex flex-col items-end pb-6">
            <div>
                <a href="/new-word?grade=${grade}&lesson=${lesson}&forUser=${forUser}"
                   class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                    Add word...
                </a>
            </div>
        </div>
        <div class="grid gap-6 mb-6 md:grid-cols-3">
            <div>
                <label for="forUser" class="block mb-2 text-sm font-medium text-gray-900">For user</label>
                <p id="forUser"
                   class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm bg-gray-200">
                    ${forUser}
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
                <label for="lesson" class="block mb-2 text-sm font-medium text-gray-900">Lesson</label>
                <p id="lesson"
                   class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm bg-gray-200">
                    ${lesson}
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
                                       href="/manage-words?grade=${grade}&lesson=${lesson}&forUser=${forUser}&page=${pageNumber - 1}">
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