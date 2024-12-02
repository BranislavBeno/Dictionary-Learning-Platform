@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param String question
@param String answer

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Guess word</h2>
        <form action="/next-word" method="POST">
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
                    <label for="answer" class="block mb-2 text-sm font-medium text-green-700">Answer</label>
                    <p id="answer"
                       class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-green-500 text-green-900 bg-green-50 sm:text-sm">
                        ${answer}
                    </p>
                    <p class="mt-2 text-sm text-green-600"><span class="font-medium">Great!</span> Your answer is correct.</p>
                </div>
            </div>
            <div>
                <button type="submit" autofocus
                        class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    Next
                </button>
            </div>
        </form>
    </div>
`)