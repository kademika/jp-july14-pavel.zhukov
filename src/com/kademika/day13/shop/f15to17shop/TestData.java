package com.kademika.day13.shop.f15to17shop;

import com.kademika.day13.shop.f15to17shop.personal.JobRole;
import com.kademika.day13.shop.f15to17shop.personal.Personal;
import com.kademika.day13.shop.f15to17shop.products.Audio;
import com.kademika.day13.shop.f15to17shop.products.AudioType;
import com.kademika.day13.shop.f15to17shop.products.Component;
import com.kademika.day13.shop.f15to17shop.products.Computer;
import com.kademika.day13.shop.f15to17shop.products.ComputerType;
import com.kademika.day13.shop.f15to17shop.products.Phone;
import com.kademika.day13.shop.f15to17shop.products.PhoneType;
import com.kademika.day13.shop.f15to17shop.products.Product;
import com.kademika.day13.shop.f15to17shop.products.Video;
import com.kademika.day13.shop.f15to17shop.products.VideoType;

public class TestData {
	private int i;

	public Product[] arrayProducts() {
		Product[] products = new Product[50];

		products[i++] = new Computer("0001", "Asus CM6830-RU003D", "Taiwan",
				"PC for game, weight: 9 kg", "black", ComputerType.PC,
				"Intel Core i5", 3.3, "DDR3 SoDIMM", 4096, "Seagate Barracuda",
				1000, "Windows 8", 1, 26990);
		products[i++] = new Computer("0002", "HP Envy Phoenix 810-002er",
				"USA", "PC for game, weight: 12,5 kg", "silver, black",
				ComputerType.PC, "Intel Core i5", 3.1, "DDR3 DIMM", 8192,
				"Seagate Barracuda", 2000, "Windows 8", 2, 39990);
		products[i++] = new Computer("0003", "Acer Aspire MC605", "Taipei",
				"PC for home, weight: 9 kg", "black", ComputerType.PC,
				"Intel Core i3", 3.3, "DDR3 DIMM", 4096, "Seagate Barracuda",
				1000, "Windows 8", 1, 19990);
		products[i++] = new Computer("0004", "Lenovo IC C440 Ci5-3330S",
				"China", "Monoblock, weight: 6,44 kg", "black",
				ComputerType.PC, "Intel Core i3", 3.3, "DDR3 DIMM", 4096,
				"Seagate Barracuda", 1000, "Windows 8", 1, 29990);
		products[i++] = new Computer("0005", "Dell Latitude 5430-E117", "USA",
				"Compact and light, weight: 2,04 kg", "black",
				ComputerType.NOTEBOOK, "Intel Core i3", 2.4, "DDR3 SoDIMM",
				4096, "Seagate Barracuda", 500, "Windows 7", 1, 30390);
		products[i++] = new Computer("0006", "Toshiba Portege Z10T-A-L4S",
				"Japan", "Ultrabook, weight: 1,45 kg", "silver",
				ComputerType.NOTEBOOK, "Intel Core i5", 1.5, "DDR3 SoDIMM",
				4096, "Seagate Barracuda", 500, "Windows 8", 1, 41990);
		products[i++] = new Computer("0007", "Acer Aspire V5-552P-85556G50amm",
				"Taipei", "Notebook for home, weight: 2,2 kg", "gold",
				ComputerType.NOTEBOOK, "AMD A8", 2.1, "DDR3 SoDIMM", 6144,
				"Seagate Barracuda", 500, "Windows 8", 1, 20990);
		products[i++] = new Computer("0008", "ASUS VivoTab Note 8", "Taiwan",
				"Windows-tabletPC, weight: 380 g", "black",
				ComputerType.TABLETPC, "Atom Z3740", 1.3, "DDR3", 2048,
				"Internal memory", 32, "Windows 8", 1, 13990);
		products[i++] = new Computer("0009", "Apple iPad mini Retina", "USA",
				"Apple iPad, weight: 331 g", "silver", ComputerType.TABLETPC,
				"Apple A7", 1.3, "DDR3", 1024, "Internal memory", 64, "iOS", 2,
				23990);
		products[i++] = new Computer("0010", "Lenovo IdeaTab S6000L", "China",
				"Android-tabletPC, weight: 562 g", "black",
				ComputerType.TABLETPC, "MediaTek", 1.2, "DDR3", 1024,
				"Internal memory", 16, "Android 4.2", 2, 9990);

		products[i++] = new Video("0101", "Philips 46PFL4988T/60",
				VideoType.TV, "Netherlands", "1080p Full HD", "black", "LED",
				46, "DVB-C, DVB-T, DVB-T2, NTSC, PAL, SECAM", "1920×1080", 4,
				31000);
		products[i++] = new Video("0102", "LG 47LA615V", VideoType.TV,
				"South Korea", "1080p Full HD", "black", "LED", 47,
				"PAL, SECAM, NTSC, DVB-T2, DVB-C, DVB-S2, DVB-T, DVB-S",
				"1920×1080", 3, 24250);
		products[i++] = new Video("0103", "Sony KDL42W653ABR", VideoType.TV,
				"Japan", "1080p Full HD", "black", "LED", 42,
				"PAL, SECAM, NTSC, DVB-T2, DVB-C, DVB-T", "1920×1080", 5, 26990);
		products[i++] = new Video("0104", "Samsung UE-55F6400AK", VideoType.TV,
				"South Korea", "1080p Full HD", "black", "LED", 55,
				"PAL, SECAM, NTSC, DVB-T2, DVB-C", "1920×1080", 4, 59990);
		products[i++] = new Video("0105", "Sony BDP-S4100B", VideoType.PLAYER,
				"Japan", "Blu-Ray", "black", "DVD-Video, MP3, WMA", 6, 3999);
		products[i++] = new Video("0106", "BBK DVP964HD", VideoType.PLAYER,
				"China", "DVD-player", "silver",
				"DVD-Video, MPEG4/DivX, Super VideoCD, VideoCD", 5, 1799);
		products[i++] = new Video("0107", "Loeffen Lf-DV-705",
				VideoType.PLAYER, "Sweden", "DVD-player", "black",
				"DVD-Video, VideoCD, Super VideoCD, MPEG4/DivX", 7, 699);
		products[i++] = new Video("0108", "BenQ MS504", VideoType.PROJECTOR,
				"Taiwan", "Office projector", "black", "NTSC, PAL, SECAM",
				"VGA (640 x 480) до UXGA (1600 x 1200)", 190, 5, 12700);
		products[i++] = new Video("0109", "Philips PPX3610",
				VideoType.PROJECTOR, "Netherlands", "Office projector",
				"black", "NTSC, PAL, SECAM",
				"480i, 480p, 576i, 576p, 720p, 1080i, 1080p", 230, 4, 19900);
		products[i++] = new Video("0110", "Acer K135", VideoType.PROJECTOR,
				"Taipei", "HD projector", "white", "NTSC, PAL, SECAM",
				"480i, 480p, 576i, 576p, 720p, 1080i", 350, 4, 22000);

		products[i++] = new Audio("0201", "JBL LOFT50", AudioType.LOUDSPEAKERS,
				"USA", "Passive loudspeakers", "black", 250, "jack 3.5",
				"System 2.0", 4, 10990);
		products[i++] = new Audio("0202", "Yamaha YST-SW012",
				AudioType.LOUDSPEAKERS, "Japan", "acoustic system", "black",
				100, "jack 3.5", "Subwoofer", 3, 3290);
		products[i++] = new Audio("0203", "Genius SW-G2.1 3000",
				AudioType.LOUDSPEAKERS, "Taipei",
				"Loudspeakers with subwoofer", "black", 70, "jack 3.5",
				"System 2.1", 3, 3990);
		products[i++] = new Audio("0204", "Harman/Kardon AVR 151/230",
				AudioType.STEREO, "USA", "AV receiver", "black", 75,
				"aux, jack 3.5", "System 5.1", 2, 11990);
		products[i++] = new Audio("0205", "Sony HT-CT660", AudioType.STEREO,
				"Japan", "Soundbar", "black", 330, "aux, jack 3.5",
				"System 2.1", 2, 12990);
		products[i++] = new Audio("0206", "Philips HTS5120/51",
				AudioType.STEREO, "Netherlands", "Soundbar", "black", 250,
				"aux, jack 3.5", "System 5.1", 2, 8990);
		products[i++] = new Audio("0207", "Beats Pro", AudioType.HEADPHONES,
				"Taiwan", "Hi-Fi", "white", 96, "jack 3.5", "headband", 6,
				12490);
		products[i++] = new Audio("0208", "AKG K551", AudioType.HEADPHONES,
				"Austria", "Monitor headphones", "silver, black", 78,
				"jack 3.5", "headband", 3, 4990);
		products[i++] = new Audio("0209", "Ritmix RDM-111",
				AudioType.MICROPHONE, "South Korea", "Table microphone",
				"black", 8, "jack 3.5", "Computer microphone", 8, 399);
		products[i++] = new Audio("0210", "Defender MIC-155",
				AudioType.MICROPHONE, "Russia", "Karaoke microphone", "black",
				8, "jack 3.5", "Vocal microphone", 6, 999);

		products[i++] = new Phone("0301", "HTC Desire 601",
				PhoneType.SMARTPHONE, "Taiwan", "Dual Sim", 4.5, "540x960",
				"Android 4.2", "Micro-SIM", "white", 6, 13990);
		products[i++] = new Phone("0302", "LG D821 Nexus 5",
				PhoneType.SMARTPHONE, "South Korea", "One Sim", 4.95,
				"1920 x 1080", "Android 4.4", "Micro-SIM", "black", 6, 18990);
		products[i++] = new Phone("0303", "Samsung GT-i9192 Galaxy S4 mini",
				PhoneType.SMARTPHONE, "South Korea", "One Sim", 4.3,
				"540 x 960", "Android 4.2", "Micro-SIM", "yellow", 5, 15490);
		products[i++] = new Phone("0304", "Nokia Lumia 625",
				PhoneType.SMARTPHONE, "Finland", "One Sim", 4.7, "480x800",
				"Windows Phone 8", "Micro-SIM", "black", 6, 10990);
		products[i++] = new Phone("0305", "Apple iPhone 4S",
				PhoneType.SMARTPHONE, "USA", "One Sim", 3.5, "640×960",
				"iOS 5", "Micro-SIM", "black", 8, 21990);
		products[i++] = new Phone("0306", "Nokia Asha 500 Dual SIM",
				PhoneType.MOBILEPHONE, "Finland", "Dual Sim", 2.8, "240x320",
				"Asha", "Micro-SIM", "red", 4, 2390);
		products[i++] = new Phone("0307", "Philips Xenium X5500",
				PhoneType.MOBILEPHONE, "Netherlands", "One Sim", 2.6,
				"240x320", "Philips OS", "SIM", "black", 4, 5390);
		products[i++] = new Phone("0308", "Samsung GT-C3011",
				PhoneType.MOBILEPHONE, "South Korea", "One Sim", 2.0,
				"128x160", "MIDP2.0 CLDC1.1 JAVA", "SIM", "blue", 4, 1490);
		products[i++] = new Phone("0309", "PANASONIC KX-TG1611RUH",
				PhoneType.RADIOPHONE, "Japan", "DECT RADIOPHONE", 1.5,
				"128x160", "PANASONIC OS", "none", "black", 4, 899);
		products[i++] = new Phone("0310", "GIGASET A420 AM",
				PhoneType.RADIOPHONE, "Germany", "DECT RADIOPHONE", 1.5,
				"128x160", "GIGASET OS", "none", "black", 4, 1439);

		products[i++] = new Component("0401",
				"SmartTrack DVD+R 4.7 Gb 16x 25 pcs.", "Russia", "DVD-R discs",
				"green", 100, 369);
		products[i++] = new Component("0402", "Verbatim CD-R 700 Mb 48x/52x",
				"Russia", "CD-R", "blue", 100, 45);
		products[i++] = new Component("0403", "Hama 120 CD", "Russia",
				"Purse for discs", "grey", 15, 499);
		products[i++] = new Component("0404", "Hama 30 CD", "Russia",
				"Front discs", "black", 10, 799);
		products[i++] = new Component("0405", "Lomond Photo A4 210g/m",
				"Russia", "Photo paper", "white", 50, 269);
		products[i++] = new Component("0406", "SvetoCopy A4 80g/m", "Russia",
				"List paper", "white", 200, 169);
		products[i++] = new Component("0407", "Canon PGI-455PGBK XXL", "Japan",
				"Cartridge for inkjet printers", "black", 10, 1090);
		products[i++] = new Component("0408", "Kyocera Mita TK-1110", "Japan",
				"Cartridge for laser printers", "black", 6, 2420);
		products[i++] = new Component("0409", "HP CE278A", "USA",
				"Cartridge for laser printers", "black", 10, 790);
		products[i] = new Component("0410", "Duracell AAA (LR03) Turbo 8 pcs.",
				"USA", "Alkaline batteries", "black", 30, 439);

		return products;
	}

	public Client[] arrayClients() {
		int i = 0;
		Client[] clients = new Client[5];

		clients[i++] = new Client("Ivanov Ivan", "ivan@yandex.ru", "530215");
		clients[i++] = new Client("Petrov Petr", "petr@yandex.ru", "567425");
		clients[i++] = new Client("Sidorov Semen", "semen@yandex.ru",
				"89198526344");
		clients[i++] = new Client("Litvin Andrey", "andrey@mail.ru",
				"89422312545");
		clients[i] = new Client("Smirnov Boris", "boris@gmail.com", "357446");

		return clients;
	}

	public Personal[] arrayPersonal() {
		int i = 0;
		Personal[] personal = new Personal[5];

		personal[i++] = new Personal("Shevchuk Oksana", 25, "oksana@shop_v1.ru",
				JobRole.PAYMASTER);
		personal[i++] = new Personal("Vinnikova Lilia", 32, "lilia@shop_v1.ru",
				JobRole.PAYMASTER);
		personal[i++] = new Personal("Ovchinnik Nikolay", 28,
				"nikolay@shop_v1.ru", JobRole.CONSULTANT);
		personal[i++] = new Personal("Reznikov Stas", 23, "stats@shop_v1.ru",
				JobRole.CONSULTANT);
		personal[i++] = new Personal("Pahmutov Evgeniy", 32, "evgeniy@shop_v1.ru",
				JobRole.CONSULTANT);

		return personal;
	}

	public int getI() {
		return i;
	}
}
