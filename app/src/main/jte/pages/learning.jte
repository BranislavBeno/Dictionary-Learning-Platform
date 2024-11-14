@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Choose lesson(s)</h2>
        <form>
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="grade" class="block mb-2 text-sm font-medium text-gray-900">Grade</label>
                    <input type="number" id="grade" placeholder="5" pattern="[0-9]" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 focus:z-10 sm:text-sm">
                </div>
            </div>
            <button type="submit" class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Submit</button>
        </form>
    </div>
`)