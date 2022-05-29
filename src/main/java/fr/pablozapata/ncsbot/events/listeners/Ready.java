package fr.pablozapata.ncsbot.events.listeners;

import fr.pablozapata.ncsbot.Constant;
import fr.pablozapata.ncsbot.Main;
import net.dv8tion.jda.api.events.ReadyEvent;
import org.jetbrains.annotations.NotNull;

public class Ready {
    public Ready(@NotNull ReadyEvent event) {
        final var guild = event.getJDA().getGuildById(Constant.NCS_GUILD_ID);
        Main.setNcsGuild(guild);
    }
}
