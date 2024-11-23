@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Choose lesson(s)</h2>
        <form action="/choose-lesson" method="POST">
            ${csrfHiddenInput}
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="grade" class="block mb-2 text-sm font-medium text-gray-900">Grade</label>
                    <input type="number" id="grade" name="grade" value="" placeholder="0" pattern="[0-9]" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm">
                </div>
                <div>
                    <label for="lesson" class="block mb-2 text-sm font-medium text-gray-900">Lesson(s)</label>
                    <input type="text" id="lesson" name="lesson" value="" placeholder="1,3-5" pattern="[0-9][0-9,-]*" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm">
                </div>
            </div>
            <div>
                <button type="submit"
                        class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    Submit
                </button>
            </div>
        </form>
    </div>
`)