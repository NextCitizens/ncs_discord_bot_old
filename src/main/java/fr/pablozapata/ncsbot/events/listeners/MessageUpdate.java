package fr.pablozapata.ncsbot.events.listeners;

import fr.pablozapata.ncsbot.Main;
import fr.pablozapata.ncsbot.utils.MessageUtils;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import org.jetbrains.annotations.NotNull;

public class MessageUpdate {
    public MessageUpdate(@NotNull MessageUpdateEvent event) {
        final var author = event.getAuthor();
        final var message = event.getMessage();
        final var channelType = event.getChannelType();
        final var channel = event.getChannel();
        final var member = event.getMember();
        final var guild = event.getGuild();

        if (!(channelType.isMessage() && channelType.isGuild())) {
            return;
        }

        if (!guild.equals(Main.getNcsGuild())) {
            return;
        }

        // Anti invitations system
        final var hasInvites = MessageUtils.inviteCheck(event);

        if (hasInvites) {
            message.delete().submit();
            channel.sendMessage(String.format("<:deny:980521301443760228> **»** %s les invitations Discord sont interdites !", member.getAsMention())).submit();
            return;
        }
    }
}
