<%@include file="/apps/wegmans/components/global/global.jsp" %>


<div id="communication_preferences">
    <h3>Communication Preferences</h3>

    <form>
        <div id="left" style="float: left; margin: 1.0em">
            <h4>Email Preferences</h4>
            <ul>
                <li>
                    <label for="fresh-news">
                        <input type="checkbox" name="fresh-news" id="fresh-news"/>
                        Fresh News
                    </label>
                </li>
                <li>
                    <label for="wine-news">
                        <input type="checkbox" name="wine-news" id="wine-news"/>
                        Wine News
                    </label>
                </li>
                <li>
                    <label for="opinion-panel">
                        <input type="checkbox" name="opinion-panel" id="opinion-panel"/>
                        Opinion Panel
                    </label>
                </li>
            </ul>

        </div>
        <div id="right" style="float: left; margin: 1.0em">
            <h4>Postal Mail Preferences</h4>
            <p>
                Get coupon notifications for store events, product recalls,
                discontinued items and more.
            </p>
            <p>
                <label for="fresh-news">
                    <input type="checkbox" name="fresh-news" id="fresh-news"/>
                    Fresh News
                </label>
            </p>

        </div>
        <div style="clear:both;"></div>
        <button type="submit">Save communication preferences</button>
    </form>
</div>

<w:placeholder/>
${placeholder}
