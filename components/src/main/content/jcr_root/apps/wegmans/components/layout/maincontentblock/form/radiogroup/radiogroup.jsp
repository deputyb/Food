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
%><%@include file="/libs/granite/ui/global.jsp" %><%
%><%@page session="false"
          import="java.util.Iterator,
                  com.adobe.granite.ui.components.ComponentHelper.Options,
                  com.adobe.granite.ui.components.AttrBuilder,
                  com.adobe.granite.ui.components.Config,
                  com.adobe.granite.ui.components.Field,
                  com.adobe.granite.ui.components.Tag,
                  com.adobe.granite.ui.components.Value" %>

<%-- include the radiogroup.css that prints the image to the corresponding radio button --%>
<%-- <cq:include categories="wegmans.main" /> --%>
<link rel="stylesheet" href="/etc/clientlibs/wegmans/main/css/radioGroup.css" type="text/css">

<%--###
RadioGroup
==========

.. granite:servercomponent:: /libs/granite/ui/components/foundation/form/radiogroup

   A container to group a set of radio.

   It has the following content structure:

   .. gnd:gnd::

      [granite:FormRadioGroup]
      
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
       * The name that identifies each radio when submitting the form.
       */
      - name (String)
      
      /**
       * The label of the radio group.
       */
      - text (String) i18n

      /**
       * Renders the read-only markup as well.
       */
      - renderReadOnly (Boolean)
      
      /**
       * If ``false``, the checked status is based on matching the form values by ``name`` and ``value`` properties.
       * Otherwise, the form values are not matched, and the checked status is based on ``checked`` property specified
       * by one of the radios.
       */
      - ignoreData (Boolean)

      /**
       * The available radios can be specified as content structure under ``items`` child resource. 
       */
      + items
      
   Example::
   
      + myradiogroup
        - sling:resourceType = "granite/ui/components/foundation/form/radiogroup"
        + items
          + option1
            - sling:resourceType = "granite/ui/components/foundation/form/radio"
            - name = "browser"
            - value = "ie"
            - text = "Internet Explorer"
          + option2
            - sling:resourceType = "granite/ui/components/foundation/form/radio"
            - name = "browser"
            - value = "chrome"
            - text = "Chrome"
          + option3
            - sling:resourceType = "granite/ui/components/foundation/form/radio"
            - name = "browser"
            - value = "firefox"
            - text = "Firefox"
###--%>


  <%

    Config cfg = cmp.getConfig();
    
    String name = cfg.get("name");
    String text = i18n.getVar(cfg.get("text", String.class));
    String value = cmp.getValue().getContentValue(name);
    
    Tag tag = cmp.consumeTag();
    AttrBuilder attrs = tag.getAttrs();
    
    attrs.add("id", cfg.get("id", String.class));
    attrs.addClass(cfg.get("class", String.class));
    attrs.addRel(cfg.get("rel", String.class));
    attrs.add("title", i18n.getVar(cfg.get("title", String.class)));
    
    attrs.addClass("coral-RadioGroup");

    attrs.addOthers(cfg.getProperties(), "id", "class", "rel", "title", "text", "renderReadOnly");

    if (cmp.getOptions().rootField()) {
        attrs.addClass("coral-Form-field");

        %><div class="coral-Form-fieldwrapper"><%

        if (text != null) {
            %><label class="coral-Form-fieldlabel"><%= outVar(xssAPI, i18n, text) %></label><%
        }
    }

%><div <%= attrs.build() %>><%
    if (cfg.get("renderReadOnly", false)) {
        attrs.addClass(Field.getRootClass(cfg, value));
        
        %><span class="foundation-field-editable"
            ><span class="foundation-field-readonly"><%= outVar(xssAPI, i18n, getCheckedText(value, cfg)) %></span
            ><span class="foundation-field-edit"><%
            	for (Iterator<Resource> items = cmp.getItemDataSource().iterator(); items.hasNext();) {
            	    cmp.include(items.next(), new Options().rootField(false));
                }
            %></span
        ></span><%
    } else {
    	for (Iterator<Resource> items = cmp.getItemDataSource().iterator(); items.hasNext();) {
            cmp.include(items.next(), new Options().rootField(false));
        }
    } %>
</div><%

    if (cmp.getOptions().rootField()) {
        %></div><%
    }
%><%!

private String getCheckedText(String contentValue, Config cfg) {
    for (Iterator<Resource> items = cfg.getItems(); items.hasNext();) {
        Resource item = items.next();
        Config itemCfg = new Config(item, true);
        
        String value = itemCfg.get("value", String.class);

        if (cfg.get("ignoreData", false)) {
            // "ignoreData" provided by the radio group
            if (itemCfg.get("checked", false)) {
                // "checked" provided by the radios
                return itemCfg.get("text", "");
            }
        } else {
            // mark checked if content value equals config value
            if (contentValue.equals(value)) {
                return itemCfg.get("text", "");
            }
        }
    }
    return "";
}
%>
