# Coding exercise - Zaid Ziad
<ul>
	<li>
		<b>Application installation</b>
		<ol>
			<li><a href="https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html">Install Oracle JDK 8</a> or higher</li>
			<li><a href="https://maven.apache.org/install.html">Install Apache Maven 3.3.8</a> or higher</li>
			<li>Checkout the code from git <a href="https://github.com/zaidrziad/exercise">Repo</a></li>
			<li>Navigate to the checked-out project folder</li>
			<li>Build the code and install dependencies by executing this command <b>mvn clean install</b></li>
			<li>Run the application by executing <b>mvn tomcat:run</b></li>
			<li>Open the application by navigating to <b>http://localhost:8080/</b> using any web browser</li>
		</ol>
	</li>
	<li>
		<b>Why Java?</b>
		<ol>
			<li>Java is powerful and fast to create services</li>
			<li>I am fluent in Java so it is easier for me to do it fast</li>
		</ol>
	</li>
	<li>
		<b>Assumptions</b>
		<ol>
			<li>I assumed that the max stay days should be limited so I set the limit to 60</li>
			<li>I assumed the user can search for previous start trip date.</li>
		</ol>
	</li>
	<li>
		<b>Thought about API</b>
		<ol>
			<li>I assumed that the max stay days should be limited so I set the limit to 60</li>
		</ol>
	</li>
	<li>
		<b>What want to accomplish</b>
		<ol>
			<li>Hotel offers service that validates the request before sending it to the API, so the whole focus was to make sure the validation is correct.<li>
			<li>Controllers are not included in the test, I will try to add their test.</li>
			<li>All exception error message are in formate of XXX.XXX.XXX to make localization easier and cleaner, currently the application returns the error as it is.</li>
		<ol>
	</li>
</ul>