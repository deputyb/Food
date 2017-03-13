<%@include file="/apps/wegmans/components/global/global.jsp"%>

<sl:adaptTo adaptable="${resource}" adaptTo="com.wegmans.components.account.PersonalInformationModel" var="model"/>

<div id="personal_information">
    <h3>=== My Profile ===</h3>
    <div>
        <strong id="name">Bruce Wayne</strong>
        <div id="address">123 Manor Road</div>
        <div>
            <span id="city">Gotham City, ST</span> <span id="zip">90210</span>
        </div>
    </div>
    <div><a id="edit-link" href="${model.editPageLink}">Update Profile ></a></div>
</div>


<w:placeholder />
${placeholder}
