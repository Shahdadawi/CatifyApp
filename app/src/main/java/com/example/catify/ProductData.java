package com.example.catify;

import java.util.ArrayList;

public class ProductData {

    public static ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        products.add(new Product(
                "Fancy Feast",
                "wetfood_fancyfeast",
                "Gravy Lovers Chicken Feast in Chicken Flavor Gravy Canned Cat Food, 3-oz can, case of 4",
                4.99,
                20,
                "Food",
                "WetFood"
        ));

        products.add(new Product(
                "Purina Pro Plan",
                "wetfood_purina_pro_plan",
                "Adult Seafood Stew Entree in Sauce Canned Cat Food, 3-oz can, case of 4",
                7.50,
                10,
                "food",
                "WetFood"
        ));

        products.add(new Product(
                "Blue Buffalo",
                "wetfood_bluebuffalo",
                "Tastefuls Fish & Shrimp Entrée in Gravy Flaked Wet Cat Food, 3-oz can, case of 4",
                6.50,
                15,
                "Food",
                "WetFood"
        ));

        products.add(new Product(
                "Meow Mix",
                "adultdryfood_meowmix",
                "Original Choice Dry Cat Food, 22-lb bag",
                22.50,
                15,
                "Food",
                "AdultDryFood"
        ));

        products.add(new Product(
                "Friskies",
                "adultdryfood_friskies",
                "Seafood Sensations Salmon Tuna & Shrimp Dry Cat Food, 30-lb bag",
                24.90,
                15,
                "Food",
                "AdultDryFood"
        ));

        products.add(new Product(
                "Temptations",
                "adultdryfood_temptations",
                "Adult Tasty Chicken Flavor Dry Cat Food, 20-lb bag",
                21.50,
                20,
                "Food",
                "AdultDryFood"
        ));

        products.add(new Product(
                "Lams",
                "kittendryfood_lams",
                "ProActive Health Kitten Dry Cat Food, 16-lb bag",
                33.50,
                25,
                "Food",
                "KittenDryFood"
        ));

        products.add(new Product(
                "Diamonds",
                "kittendryfood_diamond",
                "Naturals Kitten Formula Dry Cat Food, 6-lb bag",
                17.50,
                15,
                "Food",
                "KittenDryFood"
        ));

        products.add(new Product(
                "Nutro",
                "kittendryfood_nutro",
                "Wholesome Essentials Chicken & Brown Rice Recipe Kitten Dry Cat Food, 5-lb bag",
                19.90,
                15,
                "Food",
                "KittenDryFood"
        ));

        products.add(new Product(
                "Arm & Hammer",
                "litter_multicat",
                "Multi-Cat Strength Clean Burst Clumping Litter, 40-lb box",
                19.94,
                15,
                "Litter",
                "Litter"
        ));

        products.add(new Product(
                "Tidy Cats",
                "litter_tidycats",
                "Free & Clean Unscented Clumping Clay Cat Litter, 35-lb pail",
                19.68,
                15,
                "Litter",
                "Litter"
        ));

        products.add(new Product(
                "Fresh Step",
                "litter_freshstep",
                "Multi-Cat Extra Strength Scented Clumping Cat Litter, 25-lb",
                16.19,
                15,
                "Litter",
                "Litter"
        ));

        products.add(new Product(
                "Hartz",
                "treats_hartz",
                "Delectables Squeeze Up Tuna, Chicken, & Salmon Flavored Variety Pack Lickable Cat Treats, 0.5-oz tube, 54 count",
                27.62,
                10,
                "Treats",
                "Treats"
        ));

        products.add(new Product(
                "Tiki Cat",
                "treats_tikicat",
                "Stix Variety Mega Jar Lickable Cat Treats, 0.5-oz pouch, pack of 50",
                38.31,
                15,
                "Treats",
                "Treats"
        ));

        products.add(new Product(
                "Bonkers",
                "treats_bonkers",
                "Mixx Tender Chicken Flavor Grain-Free Lickable Cat Treats, 0.4-oz pouch, 4 count",
                1.98,
                30,
                "Treats",
                "Treats"
        ));

        products.add(new Product(
                "PetSafe",
                "toys_petsafe",
                "Bolt Interactive Laser Cat Toy",
                12.98,
                30,
                "Toys",
                "Toys"
        ));

        products.add(new Product(
                "SmartyKat",
                "toys_smartkat",
                "Hot Pursuit Electronic Concealed Motion Cat Toy, Blue",
                41.98,
                10,
                "Toys",
                "Toys"
        ));

        products.add(new Product(
                "SunGrow",
                "toys_sungrow",
                "Fishing Pole Teaser Wand Cat Toy with 3 Feather Teasers",
                10.98,
                10,
                "Toys",
                "Toys"
        ));

        products.add(new Product(
                "FurHaven",
                "bed_furhaven",
                "Calming Fleece Covered Dog & Cat Bed, Heather Gray",
                19.58,
                10,
                "Bed",
                "Bed"
        ));

        products.add(new Product(
                "Meowfia ",
                "bed_meowfia",
                "Premium Felt Cat Cave Bed, Medium, Rose Gold",
                39.58,
                10,
                "Bed",
                "Bed"
        ));

        products.add(new Product(
                "Frisco",
                "bed_frisco",
                "Modern Elevated Tunnel Cat Bed",
                29.98,
                15,
                "Bed",
                "Bed"
        ));


        products.add(new Product(
                "Youly Heart",
                "collar_youly",
                "YOULY Heart Breakaway Cat Collar, Small/Medium",
                9.98,
                15,
                "Accessories",
                "Collar"
        ));

        products.add(new Product(
                "Flowerlly",
                "collar_flowerlly",
                "Started As A Bottle Daisy Adjustable Cat Collar, Small/Medium",
                4.98,
                15,
                "Accessories",
                "Collar"
        ));

        products.add(new Product(
                "Youly BreakAway",
                "coolar_breakaway",
                "YOULY Breakaway Cat Collar, Blue Stripe",
                8.98,
                15,
                "Accessories",
                "Collar"
        ));

        products.add(new Product(
                "Greeny Tree",
                "tree_green",
                "Topeakmart Large Cat Tree Tower with 2 Condos, 62.2 H, Green",
                74.98,
                15,
                "Accessories",
                "Tree"
        ));

        products.add(new Product(
                "Crazy Tree",
                "tree_crazy",
                "Topeakmart Multilevel Plush Cat Tree Play Center, Dark Gray, 53.5 H",
                68.98,
                15,
                "Accessories",
                "Tree"
        ));

        products.add(new Product(
                "Beige  Tree",
                "tree_beige",
                "Armarkat Beige Classic Real Wood Cat Tree Model A7204, 72\" H",
                132.38,
                15,
                "Accessories",
                "Tree"
        ));




        return products;
    }

}
