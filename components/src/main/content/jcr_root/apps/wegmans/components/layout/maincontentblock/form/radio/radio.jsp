<%--
  ADOBE CONFIDENTIAL

  Copyright 2012 Adobe Systems Incorporated
  All Rights Reserved.

  NOTICE:  All information contained herein is, and remains
  the property of Adobe Systems Incorporated and its suppliers,
  if any.  The intellectual and technical concepts contained
  herein are proprietary to Adobe Systems Incorporated and its
  suppliers and may be covered by U.S. and Foreign Patents,
  patents in process, and are protected by trade secret or copyright law.
  Dissemination of this information or reproduction of this material
  is strictly forbidden unless prior written permission is obtained
  from Adobe Systems Incorporated.
--%><%
%>
<%@include file="/libs/granite/ui/global.jsp" %>
<%
%><%@page session="false"
          import="com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Tag,
                  com.adobe.granite.ui.components.Value" %>


<%--###
Radio
=====

.. granite:servercomponent:: /libs/granite/ui/components/foundation/form/radio
   :supertype: /libs/granite/ui/components/foundation/form/field

   A radio component.
   
   It extends :granite:servercomponent:`Field </libs/granite/ui/components/foundation/form/field>` component.

   It has the following content structure:

   .. gnd:gnd::

      [granite:FormRadio]
      
      /**
       * The id attribute.
       */
      - id (String)

      /**
       * The class attribute. This is used to indicate the semantic relationship of the component similar to ``rel`` attribute.
       */
      - rel (String)

      /**
       * The class attribute.
       */
      - class (String)

      /**
       * The title attribute.
       */
      - title (String) i18n
      
      /**
       * The name that identifies the field when submitting the form.
       */
      - name (String)
      
      /**
       * ``true`` to generate the `SlingPostServlet @Delete <http://sling.apache.org/documentation/bundles/manipulating-content-the-slingpostservlet-servlets-post.html#delete>`_ hidden input based on the name.
       */
      - deleteHint (Boolean) = true

      /**
       * The value of the field.
       */
      - value (String)

      /**
       * Indicates if the field is in disabled state.
       */
      - disabled (Boolean)

      /**
       * Indicates if the radio is checked.
       * Providing ``checked`` property (either ``true`` or ``false``) will imply ``ignoreData`` to be ``true``. 
       */
      - checked (Boolean)

      /**
       * If ``false``, the checked status is based on matching the form values by ``name`` and ``value`` properties.
       * Otherwise, the form values are not matched, and the checked status is based on ``checked`` property specified.
       * For radios in a radio group ``ignoreData`` must be set on the group.
       */
      - ignoreData (Boolean)
      
      /**
       * The text of the radio.
       */
      - text (String) i18n
###--%><%

    Config cfg = new Config(resource, true);
    Value val = new Value(slingRequest, cfg);
    
    String name = cfg.getInherited("name");
    String value = cfg.get("value", String.class);
    boolean disabled = cfg.get("disabled", false);
    
    Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();

    attrs.add("id", cfg.get("id", String.class));
    attrs.addClass(cfg.get("class", String.class));
    attrs.addRel(cfg.get("rel", String.class));
    attrs.add("title", i18n.getVar(cfg.get("title", String.class)));
    
    attrs.add("type", "radio");
    attrs.add("name", name);
    attrs.add("value", value);
    attrs.addDisabled(disabled);

    if (cfg.get("checked", String.class) != null) {
        // providing "checked" in configuration results in ignoring content data
        attrs.addChecked(cfg.get("checked", false));
    } else if (!cfg.getInherited("ignoreData", false)) {
        // mark checked if content value equals config value
        attrs.addChecked(val.getContentValue(name).equals(value));
    }
    
    attrs.addClass("coral-Radio-input");

    attrs.addOthers(cfg.getProperties(), "id", "class", "rel", "title", "name", "value", "text", "disabled", "checked", "ignoreData");
    
    AttrBuilder rootAttrs = new AttrBuilder(request, xssAPI);
    rootAttrs.addClass("coral-Radio");
    
    if (cmp.getOptions().rootField()) {
        rootAttrs.addClass("coral-Form-field");
    }
    
    AttrBuilder deleteAttrs = new AttrBuilder(request, xssAPI);
    deleteAttrs.add("type", "hidden");
    deleteAttrs.addDisabled(disabled);
    if (name != null && name.trim().length() > 0) {
        deleteAttrs.add("name", name + "@Delete");
    }
    
%>

<label <%= rootAttrs.build() %>>
    <input <%= attrs.build() %>>
    <span class="coral-Radio-checkmark"></span>
    <span class="coral-Radio-description"><%= outVar(xssAPI, i18n, cfg.get("text", "")) %></span><%
    
    if (cfg.get("deleteHint", true)) {
        %>

	<input <%= deleteAttrs.build() %>>
	<%
    }
%>
    <%-- print image for the radio button--%>
	<%-- radio button value is the name of the corresponding css class for the image --%>
    <span class="<%= value %>"></span>
</label>

