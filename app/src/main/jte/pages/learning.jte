@import org.springframework.security.core.GrantedAuthority
@import java.util.Collection
@import com.dictionary.learning.platform.ui.CsrfHiddenInput

@param String username = ""
@param Collection<? extends GrantedAuthority> authorities = null
@param CsrfHiddenInput csrfHiddenInput

@template.layout.main(
csrfHiddenInput = csrfHiddenInput,
username = username,
content = @`
    <div class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold text-gray-800 mb-4">User Information</h2>
        <div class="space-y-4">
            <div>
                <p class="text-sm font-medium text-gray-500">Username</p>
                <p class="mt-1 text-lg text-gray-900">${username}</p>
            </div>
            @if(authorities != null && !authorities.isEmpty())
                <div>
                    <p class="text-sm font-medium text-gray-500">Roles</p>
                    <div class="mt-1 flex flex-wrap gap-2">
                        @for(var authority : authorities)
                            <span class="inline-flex items-center px-2.5 py-0.5 rounded-full text-sm font-medium bg-blue-100 text-blue-800">
                            ${authority.getAuthority()}
                        </span>
                        @endfor
                    </div>
                </div>
            @endif
        </div>
    </div>
`)