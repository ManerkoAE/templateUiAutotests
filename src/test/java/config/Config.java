package config;

import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.ConfigFactory;
import org.aeonbits.owner.Config.Key;
import org.aeonbits.owner.Config.DefaultValue;

@LoadPolicy(LoadType.MERGE)
@Sources({"system:properties", "classpath:config.properties"})
public interface Config extends org.aeonbits.owner.Config {

    @Key("explicit.wait")
    @DefaultValue("10")
    int explicitWait();

    @Key("base.bank.url")
    String baseBankUrl();

    @Key("bank.login")
    String bankLogin();

    @Key("bank.password")
    String bankPassword();

    @Key("bank.wrong.login")
    String bankWrongLogin();

    @Key("bank.wrong.password")
    String bankWrongPassword();

    @Key("clear.cookies")
    @DefaultValue("true")
    boolean clearCookies();

    @Key("hold.browser.open")
    @DefaultValue("false")
    boolean holdBrowserOpen();

    @Key("clear.report.dir")
    @DefaultValue("true")
    boolean clearReportDir();

    @Key("browser")
    @DefaultValue("chrome")
    String browser();

    Config INSTANCE = ConfigFactory.create(Config.class, System.getProperties(), System.getenv());
}