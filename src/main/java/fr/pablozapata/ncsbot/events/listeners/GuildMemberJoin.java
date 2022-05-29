package fr.pablozapata.ncsbot.events.listeners;

import fr.pablozapata.ncsbot.Constant;
import fr.pablozapata.ncsbot.Main;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import org.jetbrains.annotations.NotNull;

public class GuildMemberJoin {
    public GuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        final var guild = event.getGuild();

        if (!guild.equals(Main.getNcsGuild())) {
            return;
        }

        final var textChannel = event.getJDA().getTextChannelById(Constant.Channels.FLUX_CHANNEL_ID);
        if (textChannel == null) {
            return;
        }

        textChannel.sendMessage(String.format("<:up:980526143813877780> **»** %s", event.getMember().getAsMention())).queue();
    }
}
