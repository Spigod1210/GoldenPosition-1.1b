package org.goldenpositioning.classes.COMMANDS;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.goldenpositioning.classes.MAIN;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class null_COMMAND implements CommandExecutor {

    public static File public_saves = new File(MAIN.getPlugin().getDataFolder(), "public_saves.yml");
    public static FileConfiguration public_saves_config = YamlConfiguration.loadConfiguration(public_saves);
    public static File permanent_saves = new File(MAIN.getPlugin().getDataFolder(), "permanent_saves.yml");
    public static FileConfiguration permanent_saves_config = YamlConfiguration.loadConfiguration(permanent_saves);
    public File configfile = new File(MAIN.getPlugin().getDataFolder(), "config.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(configfile);

    public static HashMap<String, Integer> save_temp_x = new HashMap<>();
    public static HashMap<String, Integer> save_temp_y = new HashMap<>();
    public static HashMap<String, Integer> save_temp_z = new HashMap<>();

    static HashMap<String, Integer> save_semi_x = new HashMap<>();
    static HashMap<String, Integer> save_semi_y = new HashMap<>();
    static HashMap<String, Integer> save_semi_z = new HashMap<>();

    static HashMap<String, Integer> save_perm_x = new HashMap<>();
    static HashMap<String, Integer> save_perm_y = new HashMap<>();
    static HashMap<String, Integer> save_perm_z = new HashMap<>();

    List<String> temp_names = new ArrayList<>();
    List<String> semi_names = new ArrayList<>();
    List<String> perm_names = new ArrayList<>();
    List<String> public_names = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p;
            p = (Player) sender;
            if (args.length == 0) {
                p.sendMessage("§8--§2Position§8--"
                        + "\n" + "§8BlockX: §7" + p.getLocation().getBlockX()
                        + "\n" + "§8BlockY: §7" + p.getLocation().getBlockY()
                        + "\n" + "§8BlockZ: §7" + p.getLocation().getBlockZ());
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("list")) { // get list of all positions
                    if (cfg.getBoolean("Use Public Positions") == true) {

                        if (!public_saves.exists()) {
                            public_saves_config.set(p.getUniqueId() + ".saves." + "name", " ");
                            public_saves_config.set(p.getUniqueId() + ".saves." + "name", null);

                            try {
                                public_saves_config.save(public_saves);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        for (String list : public_saves_config.getConfigurationSection("Saves").getKeys(false)) {
                            public_names.addAll(Collections.singleton(list));
                        }

                        p.sendMessage("§8--Public §aPositions§8--§r" + "\n" + public_names);
                        public_names.clear();
                    }

                    if (cfg.getBoolean("Use Private Positions") == true) {

                        for (String list : permanent_saves_config.getConfigurationSection(p.getUniqueId() + ".saves").getKeys(false)) {
                            perm_names.addAll(Collections.singleton(list));
                        }

                        p.sendMessage("§8--Private §aPositions§8--§r"
                                + "\n" + "§8temporarily: §r" + temp_names
                                + "\n" + "§8semi-temporarily: §r" + semi_names
                                + "\n" + "§8permanent: §r" + perm_names);
                        perm_names.clear();

                    }

                } else if (args[0].equalsIgnoreCase("public")) { // get all pinned positions

                    if (cfg.getBoolean("Use Public Positions") == true) {

                        if (!public_saves.exists()) {
                            public_saves_config.set(p.getUniqueId() + ".saves." + "name", " ");
                            public_saves_config.set(p.getUniqueId() + ".saves." + "name", null);

                            try {
                                public_saves_config.save(public_saves);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        for (String list : public_saves_config.getConfigurationSection("Saves").getKeys(false)) {
                            public_names.addAll(Collections.singleton(list));
                        }

                        p.sendMessage("§8--Public §aPositions§8--§r" + "\n" + public_names);
                        public_names.clear();

                    }

                } else if (args[0].equalsIgnoreCase("private")) { // get list of all private Positions

                    if (cfg.getBoolean("Use Private Positions") == true) {
                        for (String list : permanent_saves_config.getConfigurationSection(p.getUniqueId() + ".saves").getKeys(false)) {
                            perm_names.addAll(Collections.singleton(list));
                        }

                        p.sendMessage("§8--Private §aPositions§8--§r"
                                + "\n" + "§8temporarily: §r" + temp_names
                                + "\n" + "§8semi-temporarily: §r" + semi_names
                                + "\n" + "§8permanent: §r" + perm_names);
                        perm_names.clear();

                    }

                } else {
                    p.sendMessage("§cincorrect Usage");
                    p.sendMessage("§7Please Use: §r"
                            + "\n" + "/position list "
                            + "\n" + "/position private"
                            + "\n" + "/position public");
                }

            } else if (args.length == 2) {
                String name = args[1];
                if (args[0].equalsIgnoreCase("player")) { // get position of player

                    Player target = Bukkit.getPlayerExact(args[1]);
                    if(!target.isOnline()) {
                        p.sendMessage("§cThe Player is not Online!");
                    } else

                    p.sendMessage("§8--§2Position§8--"
                            + "\n" + "§8Player: §a" + target.getName()
                            + "\n" + "§8BlockX: §7" + target.getLocation().getBlockX()
                            + "\n" + "§8BlockY: §7" + target.getLocation().getBlockY()
                            + "\n" + "§8BlockZ: §7" + target.getLocation().getBlockZ());

                } else if (args[0].equalsIgnoreCase("pin")) { // save Position for public
                    if (cfg.getBoolean("Use Public Positions") == true) {

                        public_saves_config.set("Saves." + name + ".BlockX", p.getLocation().getBlockX());
                        public_saves_config.set("Saves." + name + ".BlockY", p.getLocation().getBlockY());
                        public_saves_config.set("Saves." + name + ".BlockZ", p.getLocation().getBlockZ());

                        p.sendMessage("§8--§2Position§8--"
                                + "\n" + "§8Position Name: §a" + name
                                + "\n" + "§8BlockX: §7" + p.getLocation().getBlockX()
                                + "\n" + "§8BlockY: §7" + p.getLocation().getBlockY()
                                + "\n" + "§8BlockZ: §7" + p.getLocation().getBlockZ());
                        try {
                            public_saves_config.save(public_saves);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                } else if (args[0].equalsIgnoreCase("send")) { // send Player/all your coordinates
                    if (args[1].equalsIgnoreCase("all")) {
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.sendMessage("§8--§2Position§8--"
                                    + "\n" + "§8Player: §a" + p.getName()
                                    + "\n" + "§8BlockX: §7" + p.getLocation().getBlockX()
                                    + "\n" + "§8BlockY: §7" + p.getLocation().getBlockY()
                                    + "\n" + "§8BlockZ: §7" + p.getLocation().getBlockZ());
                        }
                    } else if(Bukkit.getPlayer(args[1]) != null) {
                        if(Bukkit.getPlayer(args[1]).isOnline()) {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            target.sendMessage("§8--§2Position§8--"
                                    + "\n" + "§8Player: §a" + p.getName()
                                    + "\n" + "§8BlockX: §7" + p.getLocation().getBlockX()
                                    + "\n" + "§8BlockY: §7" + p.getLocation().getBlockY()
                                    + "\n" + "§8BlockZ: §7" + p.getLocation().getBlockZ());
                        }
                    }

                } else {
                    p.sendMessage("§cincorrect Usage");
                    p.sendMessage("§7Please Use: §r"
                            + "\n" + "/position player <PlayerName>"
                            + "\n" + "/position pin <PositionName>"
                            + "\n" + "/position send <all/PlayerName>");
                }

            } else if (args.length == 3) {
                String duration = args[1];
                String name = args[2];
                if(cfg.getBoolean("Use Private Positions") == true) {
                    if (args[0].equalsIgnoreCase("save")) {

                        if (args[1].equalsIgnoreCase("temp") || args[1].equalsIgnoreCase("temporarily")) { // till Player leave Server

                            // saving to temp Hashmap
                            save_temp_x.put(name, p.getLocation().getBlockX());
                            save_temp_y.put(name, p.getLocation().getBlockY());
                            save_temp_z.put(name, p.getLocation().getBlockZ());

                            temp_names.add(name);

                            p.sendMessage("§8--§2Position§8--"
                                    + "\n" + "§7BlockX: §r" + save_temp_x.get(args[2])
                                    + "\n" + "§7BlockY: §r" + save_temp_y.get(args[2])
                                    + "\n" + "§7BlockZ: §r" + save_temp_z.get(args[2])
                                    + "\n" + "§8was saved as: §b" + args[2]
                                    + "\n" + "§8Duration: §9" + duration.replaceAll("temp", "temporarily"));

                        } else if (args[1].equalsIgnoreCase("semi") || args[1].equalsIgnoreCase("semi-temporarily")) { // till Server reboot

                            // saving to semi Hashmap
                            save_semi_x.put(args[2], p.getLocation().getBlockX());
                            save_semi_y.put(args[2], p.getLocation().getBlockY());
                            save_semi_z.put(args[2], p.getLocation().getBlockZ());

                            semi_names.add(name);

                            p.sendMessage("§8--§2Position§8--"
                                    + "\n" + "§7BlockX: §r" + save_semi_x.get(args[2])
                                    + "\n" + "§7BlockY: §r" + save_semi_y.get(args[2])
                                    + "\n" + "§7BlockZ: §r" + save_semi_z.get(args[2])
                                    + "\n" + "§8was saved as: §b" + "\n" + args[2]
                                    + "\n" + "§8Duration: §9" + duration.replaceAll("semi", "semi-temporarily"));

                        } else if (args[1].equalsIgnoreCase("perm") || args[1].equalsIgnoreCase("permanent")) { // forever

                            // saving to perm Hashmap
                            save_perm_x.put(args[2], p.getLocation().getBlockX());
                            save_perm_y.put(args[2], p.getLocation().getBlockY());
                            save_perm_z.put(args[2], p.getLocation().getBlockZ());

                            // saving to perm config
                            permanent_saves_config.set(p.getUniqueId() + ".saves." + name + ".BlockX", save_perm_x.get(name));
                            permanent_saves_config.set(p.getUniqueId() + ".saves." + name + ".BlockY", save_perm_y.get(name));
                            permanent_saves_config.set(p.getUniqueId() + ".saves." + name + ".BlockZ", save_perm_z.get(name));

                            try {
                                permanent_saves_config.save(permanent_saves);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                            p.sendMessage("§8--§2Position§8--"
                                    + "\n" + "§7BlockX: §r" + save_perm_x.get(args[2])
                                    + "\n" + "§7BlockY: §r" + save_perm_y.get(args[2])
                                    + "\n" + "§7BlockZ: §r" + save_perm_z.get(args[2])
                                    + "\n" + "§8was saved as: §b" + "\n" + args[2]
                                    + "\n" + "§8Duration: §9" + duration.replaceAll("perm", "permanent"));

                        }
                    } else if (args[0].equalsIgnoreCase("public")) { // get List of public saved Positions
                        if (cfg.getBoolean("Use Public Positions") == true) {

                            if(!public_saves.exists()) {
                                public_saves_config.set(p.getUniqueId() + ".saves." + "name", " ");
                                public_saves_config.set(p.getUniqueId() + ".saves." + "name", null);

                                try {
                                    public_saves_config.save(public_saves);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            }

                            if (args[1].equalsIgnoreCase("get")) {
                                if(public_saves_config.get("Saves." + name) != null) {

                                    int BlockX = public_saves_config.getInt("Saves." + name + ".BlockX");
                                    int BlockY = public_saves_config.getInt("Saves." + name + ".BlockY");
                                    int BlockZ = public_saves_config.getInt("Saves." + name + ".BlockZ");

                                    p.sendMessage("§8--§2Position§8--"
                                            + "\n" + "§8Position Name: §a" + name
                                            + "\n" + "§8BlockX: §7" + BlockX
                                            + "\n" + "§8BlockY: §7" + BlockY
                                            + "\n" + "§8BlockZ: §7" + BlockZ);
                                } else {
                                    p.sendMessage("§7The Position: §8" + name + " §7does not exist");
                                }
                            }
                        }
                    } else {
                        p.sendMessage("§cincorrect Usage");
                        p.sendMessage("§7Please Use: §r"
                                + "\n" + "/position save <temp,semi,perm> <PositionName>"
                                + "\n" + "/position public get <PositionName>");
                    }
                }
            } else if (args.length == 4) {
                if (cfg.getBoolean("Use Private Positions") == true) {

                    if (args[0].equalsIgnoreCase("private")) { // get List of private saved Positions
                        if (args[1].equalsIgnoreCase("get")) {
                            if (args[2].equalsIgnoreCase("temp")) {

                                if(save_temp_x != null && save_temp_y != null && save_temp_z != null) {

                                    p.sendMessage("§8--§2Position§8--"
                                            + "\n" + "§8Position Name: §a" + args[3]
                                            + "\n" + "§7BlockX: §r" + save_temp_x.get(args[3])
                                            + "\n" + "§7BlockY: §r" + save_temp_y.get(args[3])
                                            + "\n" + "§7BlockZ: §r" + save_temp_z.get(args[3]));

                                } else {
                                    p.sendMessage("§7The Position: §8" + args[3] + " §7does not exist");
                                }

                            } else if (args[2].equalsIgnoreCase("semi")) {

                                if(save_semi_x != null && save_semi_y != null && save_semi_z != null) {

                                    p.sendMessage("§8--§2Position§8--"
                                            + "\n" + "§8Position Name: §a" + args[3]
                                            + "\n" + "§7BlockX: §r" + save_semi_x.get(args[3])
                                            + "\n" + "§7BlockY: §r" + save_semi_y.get(args[3])
                                            + "\n" + "§7BlockZ: §r" + save_semi_z.get(args[3]));

                                } else {
                                    p.sendMessage("§7The Position: §8" + args[3] + " §7does not exist");
                                }

                            } else if (args[2].equalsIgnoreCase("perm")) {

                                if(permanent_saves_config.get(p.getUniqueId() + ".Saves." + args[3]) != null) {

                                    int BlockX = permanent_saves_config.getInt(p.getUniqueId() + ".Saves." + args[3] + ".BlockX");
                                    int BlockY = permanent_saves_config.getInt(p.getUniqueId() + ".Saves." + args[3] + ".BlockY");
                                    int BlockZ = permanent_saves_config.getInt(p.getUniqueId() + ".Saves." + args[3] + ".BlockZ");

                                    p.sendMessage("§8--§2Position§8--"
                                            + "\n" + "§8Position Name: §a" + args[3]
                                            + "\n" + "§7BlockX: §r" + BlockX
                                            + "\n" + "§7BlockY: §r" + BlockY
                                            + "\n" + "§7BlockZ: §r" + BlockZ);
                                } else {
                                    p.sendMessage("§7The Position: §8" + args[3] + " §7does not exist");
                                }

                            }
                        }
                    } else {
                        p.sendMessage("§cincorrect Usage");
                        p.sendMessage("§7Please Use: §r"
                                + "\n" + "/position private get <temp,semi,perm> <PositionName>");
                    }
                }
            }
        } else {
            sender.sendMessage("§6" + MAIN.getPlugin().getName() + " §8»" + "§r For that you need to be a Player!");
        }
        return false;
    }
}
