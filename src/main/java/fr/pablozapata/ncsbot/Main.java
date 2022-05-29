package fr.pablozapata.ncsbot;

import fr.pablozapata.ncsbot.events.EventHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class Main {
    private static JDA bot;
    private static Guild ncsGuild;

    public static void main(String[] args) throws LoginException {
        final var token = System.getenv("NCSBOT_DISCORD_TOKEN");
        final var builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.streaming(".gg/nextcitizens", "https://www.twitch.tv/pablo_1610"));
        builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.addEventListeners(new EventHandler());
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);
        builder.enableCache(
                CacheFlag.ACTIVITY, CacheFlag.CLIENT_STATUS, CacheFlag.EMOTE, CacheFlag.MEMBER_OVERRIDES,
                CacheFlag.ROLE_TAGS, CacheFlag.VOICE_STATE, CacheFlag.ONLINE_STATUS
        );

        bot = builder.build();
    }

    public static JDA getBot() {
        return bot;
    }

    public static Guild getNcsGuild() {
        return ncsGuild;
    }

    public static void setNcsGuild(Guild ncsGuild) {
        Main.ncsGuild = ncsGuild;
    }
}
