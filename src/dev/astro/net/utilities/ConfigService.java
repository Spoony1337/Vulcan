package dev.astro.net.utilities;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.chat.CC;

public class ConfigService {

    private String NO_PLAYER_FOUND;
    private String NO_PERMISSION;
    private String ABILITY_DISABLED;
    private String ON_ABILITY_USE_IN_REGION;
    private String TARGET_IS_IN_REGION;
    public ConfigService(){
        NO_PLAYER_FOUND = Vulcan.getInstance().getConfig().getString("Messages.NoPlayerFound");
        NO_PERMISSION = Vulcan.getInstance().getConfig().getString("Messages.NoPermissions");
        ABILITY_DISABLED = Vulcan.getInstance().getConfig().getString("Messages.AbilityDisabled");
        ON_ABILITY_USE_IN_REGION = Vulcan.getInstance().getConfig().getString("Messages.Region.OnAbilityUse");
        TARGET_IS_IN_REGION = Vulcan.getInstance().getConfig().getString("Messages.Region.OnTargetIsInRegion");
    }

    public String NO_PLAYER_FOUND() { return CC.translate(NO_PLAYER_FOUND); }
    public String NO_PERMISSION(){ return CC.translate(NO_PERMISSION); }
    public String ABILITY_DISABLED() { return CC.translate(ABILITY_DISABLED);}
    public String ON_ABILITY_USE_IN_REGION() { return CC.translate(ON_ABILITY_USE_IN_REGION);}
    public String TARGET_IS_IN_REGION() { return CC.translate(TARGET_IS_IN_REGION); }
}
