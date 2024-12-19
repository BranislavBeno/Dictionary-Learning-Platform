@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param String forUser
@param int grade
@param int lesson

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Add word</h2>
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
        <form action="/add-word" method="POST" autocomplete="off">
            ${csrfHiddenInput}
            <input type="hidden" name="forUser" value="${forUser}">
            <input type="hidden" name="grade" value="${grade}">
            <input type="hidden" name="lesson" value="${lesson}">
            <div class="grid gap-6 mb-6 md:grid-cols-2">
                <div>
                    <label for="english" class="block mb-2 text-sm font-medium text-gray-900">In english</label>
                    <input type="text" id="english" name="english" autofocus required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                </div>
                <div>
                    <label for="slovak" class="block mb-2 text-sm font-medium text-gray-900">In slovak</label>
                    <input type="text" id="slovak" name="slovak" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                </div>
            </div>
            <div>
                <button type="submit"
                        class="group relative w-full flex justify-center py-2 px-4 border border-transparent text-sm font-medium rounded-md text-white bg-blue-500 hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                    Submit
                </button>
            </div>
        </form>
    </div>
`)