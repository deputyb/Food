<%@include file="/apps/wegmans/components/global/global.jsp"%><%@ page import="com.day.cq.i18n.I18n,
                                          com.day.cq.tagging.TagManager,
                                          com.day.cq.wcm.foundation.Search" %>
<!-- ================================ Search Start ================================ -->
<w:placeholder />
<w:search />
<cq:setContentBundle source="page" />

<c:set var="nextText"><cq:text property="nextText" placeholder="Next" /></c:set>
<c:set var="noResultsText"><cq:text property="noResultsText" placeholder="No results found." /></c:set>
<c:set var="previousText"><cq:text property="previousText" placeholder="Previous" /></c:set>
<c:set var="relatedSearchesText"><cq:text property="relatedSearchesText" placeholder="Related Searches" /></c:set>
<c:set var="resultPagesText"><cq:text property="resultPagesText" placeholder="Search Results:" /></c:set>
<c:set var="searchButtonText"><cq:text property="searchButtonText" placeholder="Search Button Text" /></c:set>
<c:set var="searchTrendsText"><cq:text property="searchTrendsText" placeholder="Search Trends" /></c:set>
<c:set var="similarPagesText"><cq:text property="similarPagesText" placeholder="Similar Pages" /></c:set>
<c:set var="spellcheckText"><cq:text property="spellcheckText" placeholder="Did you mean?" /></c:set>
<c:set var="statisticsText"><cq:text property="statisticsText" placeholder="" /></c:set>
<c:set var="bucketValue"><cq:text property="bucketValue" placeholder="" /></c:set>

<c:set var="trends" value="${search.trends}"/><c:set var="result" value="${result}"/>
<%TagManager tm = resourceResolver.adaptTo(TagManager.class);%>

<center>
    <form action="${currentPage.path}.html">
        <input size="41" maxlength="2048" name="q" value="${escapedQueryForAttr}"/>
        <input value="${searchButtonText}" type="submit"/>
    </form>
</center>
<br/>
<c:choose>
    <c:when test="${empty result && empty escapedQuery}">
    </c:when>
    <c:when test="${empty result.hits}">
        ${result.trackerScript}
        <c:if test="${result.spellcheck != null}">
            <p>${spellcheckText} <a href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${result.spellcheck}"/></c:url>"><b><c:out value="${result.spellcheck}"/></b></a></p>
                    </c:if>
                    ${noResultsText}
    </c:when>
    <c:otherwise>
        ${result.trackerScript}
        ${statisticsText}
        <div class="searchRight">
            <c:if test="${fn:length(trends.queries) > 0}">
                <p>${searchTrendsText}</p>
                <div class="searchTrends">
                    <c:forEach var="query" items="${trends.queries}">
                        <a href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${query.query}"/></c:url>"><span style="font-size:${query.size}px"><c:out value="${query.query}"/></span></a>
                        </c:forEach>
                </div>
            </c:if>

            <c:if test="${result.facets.languages.containsHit}">
                <p>Languages</p>
                <c:forEach var="bucket" items="${result.facets.languages.buckets}">
                    <c:set var="bucketValue" value="${bucket.value}"/>
                    <c:set var="label" value="${bucketValue}"/>
                    <c:choose>
                        <c:when test="${param.language != null}">
                            ${label} (${bucket.count}) - <a title="filter results" href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${escapedQueryForHref}"/></c:url>">remove filter</a>
                        </c:when>
                        <c:otherwise>
                            <a title="filter results" href="
                               <c:url value="${currentPage.path}.html">
                                   <c:param name="q" value="${escapedQueryForHref}"/>
                                   <c:param name="language" value="${bucket.value}"/>
                               </c:url>">
                                ${label} (${bucket.count})
                            </a>
                        </c:otherwise>
                    </c:choose><br/>
                </c:forEach>
            </c:if>

            <c:if test="${result.facets.tags.containsHit}">
                <p>Tags</p>
                <c:forEach var="bucket" items="${result.facets.tags.buckets}">
                    <c:set var="bucketValue" value="${bucket.value}"/>
                    <c:set var="tag" value="<%= tm.resolve((String) pageContext.getAttribute("bucketValue")) %>"/>
                    <c:if test="${tag != null}">
                        <c:set var="label" value="${tag.title}"/>
                        <c:choose>
                            <c:when test="${not empty param.tag and fn:contains(param.tag, bucketValue)}">
                                ${label} (${bucket.count}) - <a title="filter results" href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${escapedQueryForHref}"/></c:url>">remove filter</a>
                            </c:when>
                            <c:otherwise>
                                <a title="filter results" href="
                                   <c:url value="${currentPage.path}.html">
                                       <c:param name="q" value="${escapedQueryForHref}"/>
                                       <c:param name="tag" value="${bucket.value}"/>
                                   </c:url>">
                                    ${label} (${bucket.count})
                                </a>
                            </c:otherwise>
                        </c:choose><br/>
                    </c:if>
                </c:forEach>
            </c:if>

            <c:if test="${result.facets.mimeTypes.containsHit}">
                <jsp:useBean id="fileTypes" class="com.day.cq.wcm.foundation.FileTypes"/>
                <p>File types</p>
                <c:forEach var="bucket" items="${result.facets.mimeTypes.buckets}">
                    <c:set var="bucketValue" value="${bucket.value}"/>
                    <c:set var="label" value="${fileTypes[bucket.value]}"/>
                    <c:choose>
                        <c:when test="${not empty param.mimeType and fn:contains(param.mimeType, bucketValue)}">
                            ${label} (${bucket.count}) - <a title="filter results" href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${escapedQueryForHref}"/></c:url>">remove filter</a>
                        </c:when>
                        <c:otherwise>
                            <a title="filter results" href="
                               <c:url value="${currentPage.path}.html">
                                   <c:param name="q" value="${escapedQueryForHref}"/>
                                   <c:param name="mimeType" value="${bucket.value}"/>
                               </c:url>">
                                ${label} (${bucket.count})
                            </a>
                        </c:otherwise>
                    </c:choose><br/>
                </c:forEach>
            </c:if>

            <c:if test="${result.facets.lastModified.containsHit}">
                <p>Last Modified</p>
                <c:forEach var="bucket" items="${result.facets.lastModified.buckets}">
                    <c:choose>
                        <c:when test="${param.from == bucket.from && param.to == bucket.to}">
                            ${bucket.value} (${bucket.count}) - <a title="filter results" href="<c:url value="${currentPage.path}.html"><c:param name="q" value="${escapedQueryForHref}"/></c:url>">remove filter</a>
                        </c:when>
                        <c:otherwise>
                            <a title="filter results" href="
                               <c:url value="${currentPage.path}.html">
                                   <c:param name="q" value="${escapedQueryForHref}"/>
                                   <c:param name="from" value="${bucket.from}"/>
                                   <c:if test="${bucket.to != null}">
                                       <c:param name="to" value="${bucket.to}"/>
                                   </c:if>
                               </c:url>">
                                ${bucket.value} (${bucket.count})
                            </a>
                        </c:otherwise>
                    </c:choose><br/>
                </c:forEach>
            </c:if>
        </div>

        <c:if test="${fn:length(search.relatedQueries) > 0}">
            <br/><br/>
            ${relatedSearchesText}
            <c:forEach var="rq" items="${search.relatedQueries}">
                <a style="margin-right:10px" href="${currentPage.path}.html?q=${rq}"><c:out value="${rq}"/></a>
            </c:forEach>
        </c:if>
        <br/>
        <c:forEach var="hit" items="${result.hits}" varStatus="status">
            <br/>
            <c:if test="${hit.extension != \"\" && hit.extension != \"html\"}">
                <span class="icon type_${hit.extension}"><img src="/etc/designs/default/0.gif" alt="*"></span>
                </c:if>
            <a href="${hit.URL}" onclick="trackSelectedResult(this, ${status.index + 1})">${hit.title}</a>
            <div>${hit.excerpt}</div>
            ${hit.URL}<c:if test="${!empty hit.properties['cq:lastModified']}"> - <c:catch><fmt:formatDate value="${hit.properties['cq:lastModified'].time}" dateStyle="medium"/></c:catch></c:if> - <a href="${hit.similarURL}">${similarPagesText}</a>
                    <br/>
        </c:forEach>
        <br/>
        <c:if test="${fn:length(result.resultPages) > 1}">
            ${resultPagesText}
            <c:if test="${result.previousPage != null}">
                <a href="${result.previousPage.URL}">${previousText}</a>
            </c:if>
            <c:forEach var="page" items="${result.resultPages}">
                <c:choose>
                    <c:when test="${page.currentPage}">${page.index + 1}</c:when>
                    <c:otherwise>
                        <a href="${page.URL}">${page.index + 1}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${result.nextPage != null}">
                <a href="${result.nextPage.URL}">${nextText}</a>
            </c:if>
        </c:if>
    </c:otherwise>
</c:choose>
${placeholder}
<!-- ================================ Search End ================================ -->
