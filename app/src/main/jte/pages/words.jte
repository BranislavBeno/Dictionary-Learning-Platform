@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import com.dictionary.learning.platform.word.WordDto
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<WordDto> words

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <div class="flex flex-col items-end pb-6">
            <div>
                <a href="/add-word"
                   class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600">
                    Add word...
                </a>
            </div>
        </div>
        <div class="relative overflow-x-auto">
            <table class="w-full text-sm text-left rtl:text-right text-gray-500 dark:text-gray-400">
                <thead class="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
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
        </div>
    </div>
`)