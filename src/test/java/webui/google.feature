Feature: Google Application

  Background:
    * def perfMgr = Java.type('webui.PerfManager')
    * configure driver = { type: 'chrome', headless : true, showDriverLog : true, showProcessLog : true, addOptions : ['--incognito'] }

  Scenario: Google Search Karate
    * def p1 = perfMgr.startPerfEvent('Google: Launch Website')
    Given driver 'https://google.com'
    * perfMgr.stopPerfEvent(p1, {}, karate)
    And input("input[name=q]", 'karate dsl')
    * def p2 = perfMgr.startPerfEvent('Google: Search Karate')
    When submit().click("input[name=btnI]")
    Then waitForUrl('https://github.com/intuit/karate')
    * perfMgr.stopPerfEvent(p2, {}, karate)

  Scenario: Google Search Prashant Patil
    * def p1 = perfMgr.startPerfEvent('Google: Launch Website')
    Given driver 'https://google.com'
    * perfMgr.stopPerfEvent(p1, {}, karate)
    And input("input[name=q]", 'Prashant Patil Linkedin dell')
    * def p2 = perfMgr.startPerfEvent('Google: Search Karate')
    When submit().click("input[name=btnK]")
    Then waitFor("div[id=result-stats]")
    * perfMgr.stopPerfEvent(p2, {}, karate)