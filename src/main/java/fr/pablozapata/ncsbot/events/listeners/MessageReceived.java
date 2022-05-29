package fr.pablozapata.ncsbot.events.listeners;

import fr.pablozapata.ncsbot.Main;
import fr.pablozapata.ncsbot.utils.MessageUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import fr.pablozapata.ncsbot.Constant;

public class MessageReceived {
    public MessageReceived(@NotNull MessageReceivedEvent event) {
        final var author = event.getAuthor();
        final var message = event.getMessage();
        final var channelType = event.getChannelType();
        final var channel = event.getChannel();
        final var member = event.getMember();
        final var guild = event.getGuild();
        boolean is_using_blacklisted_term = false;
        
        if (!(channelType.isMessage() && channelType.isGuild())) {
            return;
        }

        if (!guild.equals(Main.getNcsGuild())) {
            return;
        }
      // Loop to check Blacklisted terms
        for (String i : Constant.Blacklisted_terms) {
            if (message.getContentRaw().contains(i)) {
                message.delete().submit();
                channel.sendMessage(
                        String.format("**»** %s Tu as utilisé un mot interdit !",
                                member.getAsMention()))
                        .submit();
                is_using_blacklisted_term = true;
                return;
            } else {
                is_using_blacklisted_term = false;
            }
            // break;
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
