package me.zeroX150.authlib.login.mojang.profile;

import java.util.List;
import java.util.UUID;

/**
 * The class Minecraft profile stores uuid, username, skins and capes
 */
public class MinecraftProfile {

    private UUID uuid;
    private String username;
    private List<MinecraftProfileSkin> skins;
    private List<MinecraftProfileCape> capes;

    /**
     * Instantiates a new Minecraft profile.
     */
    public MinecraftProfile() {
    }

    /**
     * Instantiates a new Minecraft profile.
     *
     * @param uuid     the uuid
     * @param username the username
     * @param skins    the skins
     * @param capes    the capes
     */
    public MinecraftProfile(UUID uuid, String username, List<MinecraftProfileSkin> skins, List<MinecraftProfileCape> capes) {
        this.uuid = uuid;
        this.username = username;
        this.skins = skins;
        this.capes = capes;
    }

    /**
     * Gets uuid.
     *
     * @return the uuid
     */
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets skins.
     *
     * @return the skins
     */
    public List<MinecraftProfileSkin> getSkins() {
        return skins;
    }

    /**
     * Gets capes.
     *
     * @return the capes
     */
    public List<MinecraftProfileCape> getCapes() {
        return capes;
    }

    @Override
    public String toString() {
        return "MinecraftProfile{" +
                "uuid=" + uuid +
                ", username='" + username + '\'' +
                ", skins=" + skins +
                ", capes=" + capes +
                '}';
    }
}
