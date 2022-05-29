package fr.pablozapata.ncsbot.events.listeners;

import fr.pablozapata.ncsbot.Constant;
import fr.pablozapata.ncsbot.Main;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import org.jetbrains.annotations.NotNull;

public class GuildMemberRemove {
    public GuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        final var guild = event.getGuild();

        if (!guild.equals(Main.getNcsGuild())) {
            return;
        }

        final var textChannel = event.getJDA().getTextChannelById(Constant.Channels.FLUX_CHANNEL_ID);
        if (textChannel == null) {
            return;
        }

        textChannel.sendMessage(String.format("<:down:980526685529178142> **»** %s", event.getUser().getAsMention())).queue();
    }
}
