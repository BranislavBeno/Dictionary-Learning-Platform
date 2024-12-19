@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<String> users
@param String forUser
@param int grade

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Set up lesson</h2>
        <form action="/set-up-lesson" method="POST">
            ${csrfHiddenInput}
            <input type="hidden" name="forUser" value="${forUser}">
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
                    <input value="${grade}" type="number" id="grade" name="grade" pattern="[0-9]" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                </div>
                <div>
                    <label for="lesson" class="block mb-2 text-sm font-medium text-gray-900">Lesson</label>
                    <input value="1" type="number" id="lesson" name="lesson" pattern="[0-9]" required
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
