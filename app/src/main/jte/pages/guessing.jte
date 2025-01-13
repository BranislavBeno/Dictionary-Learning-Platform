@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param String question
@param String answer
@param boolean isCorrect

@template.layout.main(
pageTitle = "Guess word",
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Guess word</h2>
        <form action="/check-word" method="POST" autocomplete="off">
            ${csrfHiddenInput}
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="question" class="block mb-2 text-sm font-medium text-gray-900">Question</label>
                    <p id="question"
                       class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 text-gray-900 sm:text-sm">
                      ${question}
                    </p>
                </div>
                <div>
                @if (isCorrect)
                    <label for="answer" class="block mb-2 text-sm font-medium text-gray-900">Answer</label>
                    <input placeholder="translation" type="text" id="answer" name="answer" autofocus required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                @else
                    <label for="answer" class="block mb-2 text-sm font-medium text-red-700">Answer</label>
                    <input placeholder="${answer}" type="text" id="answer" name="answer" autofocus required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-red-500 placeholder-red-700 text-red-900 bg-red-50 focus:outline-none focus:ring-red-500 focus:border-red-500 focus:z-10 sm:text-sm">
                    <p class="mt-2 text-sm text-red-600"><span class="font-medium">Oh, no!</span> Your answer is wrong.</p>
                @endif
                </div>
            </div>
            <div>
                <button type="submit"
                        class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    Check
                </button>
            </div>
        </form>
    </div>
`)