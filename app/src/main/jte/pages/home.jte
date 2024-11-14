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
`)