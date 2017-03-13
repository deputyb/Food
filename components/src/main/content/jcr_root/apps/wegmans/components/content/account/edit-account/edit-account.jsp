<%@include file="/apps/wegmans/components/global/global.jsp" %>


<div id="edit_account">
    <h1>Edit Profile</h1>

    <p>
        If youIf your Shoppers Club number is linked to your wegmans.com account,
        any information changes made If your Shoppers Club number is linked to your
        wegmans.com account, any information changes made
    </p>

    <div>
        <form>
            <div id="left" style="float: left; margin: 1.0em">
                <h2>Username and Password</h2>
                <label for="email">
                    Email Address
                    <input type="text" id="email">
                </label>
            </div>
            <div id="right" style="float: left; margin: 1.0em">
                <h2>Contact Information</h2>

                <div>

                    <label for="first-name">
                        First Name
                        <input type="text" id="first-name">
                    </label>
                </div>

                <div>
                    <label for="last-name">
                        Last Name
                        <input type="text" id="last-name">
                    </label>
                </div>
                <div>
                    <label for="street-address">
                        Street Address
                        <input type="text" id="street-address">
                    </label>
                </div>
            </div>
            <div style="clear:both;"></div>
        </form>
        <button type="submit">Save Updates</button>
    </div>
</div>

<w:placeholder/>
${placeholder}
