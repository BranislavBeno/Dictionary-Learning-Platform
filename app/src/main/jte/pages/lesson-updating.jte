@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param long lessonId
@param int grade
@param String lesson
@param String currentUser
@param List<String> users

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">Update lesson</h2>
        <form action="/update-lesson" method="POST" autocomplete="off">
            ${csrfHiddenInput}
            <input type="hidden" name="lessonId" value="${lessonId}">
            <div class="grid gap-6 mb-6 md:grid-cols-3">
                <div>
                    <label for="lesson" class="block mb-2 text-sm font-medium text-gray-900">Lesson</label>
                    <input type="text" id="lesson" name="lesson" value="${lesson}" autofocus required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                </div>
                <div>
                    <label for="grade" class="block mb-2 text-sm font-medium text-gray-900">Grade</label>
                    <input type="text" id="grade" name="grade" value="${grade}" required
                           class="appearance-none rounded-lg relative block w-full px-3 py-2 border border-gray-300 placeholder-gray-500 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                </div>
                <div>
                    <label for="forUser" class="block mb-2 text-sm font-medium text-gray-900">For user</label>
                    <select id="forUser" name="forUser" size="${users.size()}"
                            class="appearance-none rounded-lg block w-full p-2.5 border border-gray-300 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                        @for(String user: users)
                            @if(user.equals(currentUser))
                                <option selected>${user}</option>
                            @else
                                <option>${user}</option>
                            @endif
                        @endfor
                    </select>
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