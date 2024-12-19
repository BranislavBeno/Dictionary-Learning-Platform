@import com.dictionary.learning.platform.ui.CsrfHiddenInput
@import java.util.List

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param List<String> users

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <form action="/set-up-user" method="POST">
            ${csrfHiddenInput}
            <div class="grid gap-6 mb-6 md:grid-cols-1">
                <div>
                    <label for="forUser" class="block mb-2 text-sm font-medium text-gray-900">For user</label>
                    <select id="forUser" name="forUser" size="${users.size()}"
                            class="appearance-none rounded-lg block w-full p-2.5 border border-gray-300 text-gray-900 focus:outline-none focus:ring-blue-500 focus:border-blue-500 focus:z-10 sm:text-sm">
                        @for(String user: users)
                            <option>${user}</option>
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
