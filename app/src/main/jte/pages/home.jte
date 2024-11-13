@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
    <div class="min-h-screen bg-gray-100">
        <nav class="bg-white shadow-sm">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="flex justify-between h-16">
                    <div class="flex items-center">
                        <h1 class="text-xl font-semibold">Dictionary</h1>
                    </div>
                    <div class="flex items-center space-x-4">
                        <span class="text-gray-700">Hi, ${username}</span>
                        <form action="/logout" method="post">
                            ${csrfHiddenInput}
                            <button type="submit"
                                    class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                                Logout
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
        <main class="flex items-center justify-center max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            <div class="flex flex-col items-center justify-center max-w-md space-y-8 p-6 bg-white rounded-lg shadow-md">
                <div>
                    <a href="/learning"
                       class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                        Learning
                    </a>
                </div>
                <div>
                    <a href="/administration"
                       class="px-4 py-2 bg-indigo-600 text-white rounded-md hover:bg-indigo-700">
                        Administration
                    </a>
                </div>
            </div>
        </main>
    </div>
`)