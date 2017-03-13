package com.wegmans.aem.wegmans.servlets
import com.day.cq.commons.Externalizer
import com.twcable.jackalope.impl.sling.SimpleResourceResolverFactory
import org.apache.sling.api.SlingHttpServletRequest
import org.apache.sling.api.SlingHttpServletResponse
import org.apache.sling.api.resource.ResourceResolverFactory
import org.mockito.Mockito

import static com.twcable.jackalope.JCRBuilder.*
import com.wegmans.servlets.SiteMapServlet

class SiteMapServletTest extends GroovyTestCase {

    void testDummyRequest() {
        def repo = buildRepo()
        def resolverFactory = new SimpleResourceResolverFactory(repo)

        def request = buildRequest(resolverFactory)

        StringWriter stringWriter = new StringWriter()
        def response = buildResponse(stringWriter)

        def servlet = new SiteMapServlet()
        servlet.externalizer = Mockito.mock(Externalizer.class)
        servlet.activate(Collections.emptyMap())

        servlet.doGet(request, response);

        assert "<?xml version=\"1.0\"?><urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"><url><loc></loc></url></urlset>" == stringWriter.getBuffer().toString()
    }

    static def buildRepo() {
        return repository(
            node("content",
                node("pageResource",
                    property("jcr:primaryType", "cq:Page"),
                    node("jcr:content",
                        property("preferences", "I like cheese"))))).build()
    }

    static SlingHttpServletResponse buildResponse(StringWriter stringWriter) {
        def response = Mockito.mock(SlingHttpServletResponse.class)

        PrintWriter writer = new PrintWriter(stringWriter)
        Mockito.when(response.getWriter()).thenReturn(writer)
        return response
    }

    static SlingHttpServletRequest buildRequest(ResourceResolverFactory resolverFactory) {
        def request = Mockito.mock(SlingHttpServletRequest.class)
        def resolver = resolverFactory.getAdministrativeResourceResolver(null);
        Mockito.when(request.getResourceResolver()).thenReturn(resolver)
        def resource = resolver.getResource("/content/pageResource")
        assert resource != null
        Mockito.when(request.getResource()).thenReturn(resource)
        return request;
    }
}
