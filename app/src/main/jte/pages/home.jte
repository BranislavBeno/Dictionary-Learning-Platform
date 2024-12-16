@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="flex flex-col items-center justify-center max-w-md space-y-8 p-6 bg-white rounded-lg shadow-md">
        <div>
            <a href="/learning"
               class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                Learning
            </a>
        </div>
        <div>
            <a href="/administration"
               class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                Administration
            </a>
        </div>
    </div>
`)