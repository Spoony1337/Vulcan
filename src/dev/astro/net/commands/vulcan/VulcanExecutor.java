package dev.astro.net.commands.vulcan;

import dev.astro.net.commands.vulcan.argument.ReloadArgument;
import dev.astro.net.commands.vulcan.argument.VersionArgument;
import dev.astro.net.utilities.ArgumentExecutor;

public class VulcanExecutor extends ArgumentExecutor
{
    public VulcanExecutor() {
        super("vulcan");
        this.addArgument(new VersionArgument());
        this.addArgument(new ReloadArgument());
    }
}
