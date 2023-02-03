Demonstrates differences between running the application, testing it with MockMvcBuilders and testing it with @AutoConfigureMockMvc.

If running the application, calls to http://localhost:8080 and http://localhost:8080/ have a requestUri of "/". Same for MockMvcBuilders.
If using @AutoConfigureMockMvc, requestUri returns "".

The latter causes problems when ResourceUrlEncodingFilter is active and ResourceUrlEncodingRequestWrapper throws a LookupPathIndexException when calling `mockMvc.perform(get(""))`;

When error handling is present that itself triggers a call to ResourceUrlEncodingRequestWrapper#resolveUrlPath, a ArrayIndexOutOfBoundsException is thrown because the initial request is reused and indexLookupPath is set to -1 from the previous LookupPathIndexException. To see this, enable the profile in application.properties and run the tests.