package com.example.instructionbook;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class InstructionBookCommand implements CommandExecutor {
    private final Plugin plugin;

    public InstructionBookCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    private ItemStack createInstructionBook() {
        ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle(plugin.getConfig().getString("book-title"));
        meta.setAuthor(plugin.getConfig().getString("book-author"));
        List<String> pages = new ArrayList<>();
        for (String page : plugin.getConfig().getStringList("pages")) {
            String formattedPage = ChatColor.translateAlternateColorCodes('&', page);
            formattedPage = PlaceholderAPI.setPlaceholders(null, formattedPage);
            pages.add(formattedPage);
        }
        meta.setPages(pages);
        book.setItemMeta(meta);
        return book;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("instructionbook.use")) {
                ItemStack book = createInstructionBook();
                player.openBook(book);
            }
            return true;
        }
        return false;
    }
}