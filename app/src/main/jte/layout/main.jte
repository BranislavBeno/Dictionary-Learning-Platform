@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String pageTitle = ""
@param String username = ""
@param CsrfHiddenInput csrfHiddenInput
@param gg.jte.Content content

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle} | Dictionary learning</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
    <div class="min-h-screen">
        <nav class="bg-white shadow-sm">
            <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
                <div class="flex justify-between h-16">
                    <div class="flex items-center">
                        <form action="/" method="get">
                            <button type="submit"
                                    class="px-4 py-2 bg-blue-500 text-sm text-white rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 border border-transparent">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                                    <path stroke-linecap="round" stroke-linejoin="round" d="m2.25 12 8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25" />
                                </svg>
                                <span class="sr-only">Home</span>
                            </button>
                        </form>
                    </div>
                    <div class="flex items-center space-x-4">
                        <h1 class="text-xl font-semibold">Dictionary</h1>
                    </div>
                    <div class="flex items-center space-x-4">
                        <button  type="button" id="dropdownUserButton" data-dropdown-toggle="dropdownUser"
                                 class="flex items-center text-md pe-1 font-medium text-gray-900 rounded-md hover:text-blue-600 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500  border border-transparent">
                            <span class="sr-only">Open user menu</span>
                            Hi, ${username}
                            <svg class="w-2.5 h-2.5 ms-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 10 6">
                                <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m1 1 4 4 4-4"/>
                            </svg>
                        </button>
                        <!-- Dropdown menu -->
                        <div id="dropdownUser" class="z-10 hidden bg-white divide-y divide-gray-100 rounded-lg shadow w-44">
                            <div class="py-2">
                                <form name="logoutForm" hidden="hidden" action="/logout" method="post">
                                    ${csrfHiddenInput}
                                    <input type="submit" value="Logout"/>
                                </form>
                                <a href="javascript: logoutForm.submit();" class="block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100">Sign out</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </nav>
        <main class="flex items-center justify-center max-w-7xl mx-auto py-6 sm:px-6 lg:px-8">
            ${content}
        </main>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/flowbite@2.5.2/dist/flowbite.min.js"></script>
</body>
</html>
