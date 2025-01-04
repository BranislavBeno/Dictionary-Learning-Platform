@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param double successRate

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="flex flex-col items-center justify-center bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-gray-800">You finished lesson with success rate:</h2>
        <br>
        <h1 class="text-2xl font-semibold mb-4
            @if(successRate < 0.5)
                text-red-700
            @elseif(successRate < 0.9)
                text-yellow-700
            @else
                text-green-700
            @endif
            ">${successRate * 100}%</h1>
        <div>
            <a autofocus href="/"
               class="px-4 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                OK
            </a>
        </div>
    </div>
`)