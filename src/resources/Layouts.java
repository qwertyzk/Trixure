package resources;

import logic.entities.Monster;
import logic.level.Room;

import java.util.Random;
import java.util.Stack;

public class Layouts {

	public static final int NUMBER_OF_EASY_LAYOUTS = 10;
	public static final int NUMBER_OF_MEDIUM_LAYOUTS = 5;
	public static final int NUMBER_OF_HARD_LAYOUTS = 5;

	// # wall
	// . empty
	// ^ exit
	// , trap
	// p hp_potion
	// m max_potion
	// s strength_potion
	// d defence_potion
	// ? mysterious_potion
	// G gold bag
	// ! key
	// / locked door
	// T chest
	// M mob
	// W shop

	public enum Layouts2
	{
		layout0(new String[]
					   {"##########",
						"#TTmsd?pG#",
						"#........#",
						"#........#",
						"#...TT...#",
						"#...TT...#",
						"#...TT...#",
						"#W..TT...#",
						"##.####^##",
						"##########"}, 2, 8),

		layout1(new String[]
					   {"##########################",
						"#...#....M...........#p.G#",
						"#.................M......#",
						"#..G#........M.......#..G#",
						"##.####################.##",
						"#...#pd.....##G.....G#...#",
						"#...#.......##.......#...#",
						"#.M.#,,,,,,,##..M.M..#.M.#",
						"#...#.......##.......#...#",
						"#...#......####......#...#",
						"#.../......?##?....../...#",
						"#...#......####......#...#",
						"#...#.......##.......#...#",
						"#.M.#..M.M..##,,,,,,,#.M.#",
						"#...#G......##.......#...#",
						"#...#G......##.....Gs#...#",
						"##.####################.##",
						"#T..#.............M..#G..#",
						"#..........M.............#",
						"#!GG#..M.............#..^#",
						"##########################"}, 2, 2),

		layout2(new String[]
					   {"##########################",
						"#................M.......#",
						"#####.######.###########.#",
						"#..........#.#...........#",
						"#.########.#.#.#########.#",
						"#...G#M....#.#....M..#G#.#",
						"############.#.#####.#.#.#",
						"#.....M......#....p#.#...#",
						"#.##########.#######.#####",
						"#.#......M.........#...M.#",
						"#.#.########.#####.#.###.#",
						"#.#...M...G#.#G#d#.#...#.#",
						"#.#.########.#.#.#.###.#.#",
						"#.#.......!#...#.#.#...#M#",
						"#.##############.#.#M###.#",
						"#.#.............M#.#...#.#",
						"#M#.##############.###.#.#",
						"#.#....M...........#p..#s#",
						"#.################.#######",
						"#........T#G............^#",
						"##########################"}, 1, 1),

		layout3(new String[]
					   {"##########################",
						"#p....GG##......##...M..m#",
						"#.M.....#....M...#.......#",
						"#.....................M..#",
						"#.......#........#.......#",
						"#...M...#........#.......#",
						"#.......#..M.....#...M...#",
						"#.......#........#.......#",
						"#......##.....M..##T...,,#",
						"#G....####......####...,!#",
						"############..############",
						"####^#####......##GG.#...#",
						"##.....##........#G..#.W.#",
						"#..M....#.....M..#...#...#",
						"#.......#........#...##/##",
						"#.......#...M....#.......#",
						"#...M...#........#....M..#",
						"#M.......................#",
						"#.......#..M.....#.M.....#",
						"#.#???#.##.....M##......G#",
						"##########################"}, 4, 8),

		layout4(new String[]
					   {"##########################",
						"#.^.#..................#s#",
						"#.,.#...M.....M........#/#",
						"#M..#....................#",
						"#.,.#....#####.#####...#,#",
						"#...#....#.........#...#p#",
						"#.,.#.M..#.........#...###",
						"#..M#....#..##.##..#...#m#",
						"#.,.#....#..#...#..#...#M#",
						"#...#....#M.#...#.M#...#.#",
						"#.,.#....#..#...#..#.....#",
						"#M..#.M..#..#####..#...###",
						"#.,.#....#....#....#...#T#",
						"#...#....#...!#p...#...#,#",
						"#.,.#....###########.....#",
						"#..M#...M#...#...#W#...#/#",
						"#.,.#......#...#...#...#d#",
						"#........#################",
						"#G..............M...,...T#",
						"#GGG............M...,...T#",
						"##########################"}, 14, 10),

		layout5(new String[]
				       {"############",
						"###....GG###",
						"##.....M..##",
						"###.......G#",
						"#########.##",
						"##..M.....!#",
						"#T....M...##",
						"##.#########",
						"#G.M....G###",
						"##.......^##",
						"###G.M...###",
						"############"}, 2, 2),

		layout6(new String[]
				 	   {"############",
						"#.#...G#p#s#",
						"#.#.#MG#M#M#",
						"#.#.####.#.#",
						"#........#.#",
						"###.######.#",
						"#T#........#",
						"#.#.#.######",
						"#M#.#..M...#",
						"#.#.######.#",
						"#...#^.....#",
						"############"}, 1, 1),

		layout7(new String[]
					   {"############",
						"#...#.GG...#",
						"#.../.....W#",
						"#...#G.GG..#",
						"##.#########",
						"#.....M.,.m#",
						"#.......####",
						"#...M.....G#",
						"#.........G#",
						"####..M.####",
						"#T.,......^#",
						"############"}, 2, 2),

		layout8(new String[]
					   {"############",
						"#.........p#",
						"#...M...,..#",
						"#########..#",
						"#M......,..#",
						"#..,......M#",
						"#..#########",
						"#..#......M#",
						"#.,#..#,..!#",
						"#.....#..###",
						"#GG..M#...^#",
						"############"}, 1, 1),

		layout9(new String[]
				 	   {"############",
						"#...#.!#.GG#",
						"#...#,##...#",
						"#..........#",
						"#..........#",
						"#...#MM#...#",
						"#...#MM#...#",
						"#..........#",
						"#..........#",
						"#...##/#...#",
						"#p..#T.#..^#",
						"############"}, 1, 1),

		layout10(new String[]
					   {"############",
						"####GG.p####",
						"##!......!##",
						"##....M...##",
						"#.........^#",
						"###......###",
						"#T#....../s#",
						"#./......###",
						"#T#..M.../d#",
						"###......###",
						"#W/.#??#./m#",
						"############"}, 1, 4),

		layout11(new String[]
				{"############",
						"#..........#",
						"#...,......#",
						"#...,M.....#",
						"#M..######/#",
						"#...#......#",
						"#.,,#^.....#",
						"#...########",
						"#......,...#",
						"#......,..!#",
						"#..M.......#",
						"############"}, 10, 1),

		layout12(new String[]
				{"###########################################",
						"#...........#...........#...........#....^#",
						"#.....,..M..#..M..,..M..#..M..,..M..#.....#",
						"#p....,...........,...........,..........p#",
						"###########################################"}, 1, 1),

		layout13(new String[]
				{"###########################################",
						"#^#......M.......M........M.........M..T#!#",
						"#/#..........M..................M.......#.#",
						"#.....M...............M.....M.............#",
						"###########################################"}, 1, 3),

		layout14(new String[]
				{"###########################################",
						"#........................#...............p#",
						"#....M...M...M.###.......#...M...#...M....#",
						"#!............./^#...............#.......p#",
						"###########################################"}, 21, 3),

		layout15(new String[]
				{"##############",
						"#...........p#",
						"#..##..##....#",
						"#..##..##....#",
						"#....M...M...#",
						"#.##..##..##.#",
						"#.##..##..##.#",
						"#...........T#",
						"#..###########",
						"#............#",
						"#....MMM.....#",
						"#p..........^#",
						"##############"}, 1, 1),

		layout16(new String[]
				{"##############",
						"#............#",
						"#,,,,,,,,,,,.#",
						"#............#",
						"#.,,,,,,,,.,.#",
						"#.,......,.,.#",
						"#.,.,,,,,,.,.#",
						"#.,..MM....,.#",
						"#....MM..,.,.#",
						"#.,,,,,,,,,,,#",
						"#....,...,...#",
						"#p.,...,...,^#",
						"##############"}, 1, 1),

		layout17(new String[]
				{"##############",
						"#........../^#",
						"#.############",
						"#....M.,.....#",
						"#.,......M.,.#",
						"#....,.......#",
						"#..M......,..#",
						"#......pM....#",
						"#...,...,....#",
						"#.,..M......,#",
						"#.......,M...#",
						"#..M..,.....!#",
						"##############"}, 10, 1),

		layout18(new String[]
				{"##############",
						"#............#",
						"#...M....M...#",
						"#............#",
						"#######..###/#",
						"#T..../..#T..#",
						"#######..#####",
						"#p..........!#",
						"#...M........#",
						"#..M....M....#",
						"#........M...#",
						"#!..........^#",
						"##############"}, 1, 1),

		layout19(new String[]
				{"##############",
						"#........M..T#",
						"#....#...,...#",
						"#...###..,,,M#",
						"#....#...,...#",
						"#.......,....#",
						"#...M...#p...#",
						"#.....,###,..#",
						"#.......#....#",
						"#..#....,....#",
						"#.###...M....#",
						"#.p#........^#",
						"##############"}, 1, 1),

		layout20(new String[]
				{"##############",
						"#...,...,...p#",
						"#.....,...,..#",
						"###########.,#",
						"#....!....#..#",
						"#...###...#,.#",
						"#...#^#...#..#",
						"#.....M...#.,#",
						"#.M.......#..#",
						"#.........#,.#",
						"###...M......#",
						"#T/..........#",
						"##############"}, 1, 1);


		private String[] layout;
		private int startingPosX;
		private int startingPosY;

		Layouts2(String[] layout, int startingPosX, int startingPosY)
		{
			this.layout = layout;
			this.startingPosX = startingPosX;
			this.startingPosY = startingPosY;
		}

		public static Room randomType(Random randomizer, int difficulty)
		{
			Layouts2[] layouts = values();
			Layouts2 temp = layout0;
			switch (difficulty) {
				case 0:
					break;
				case 1:
					temp = layouts[Math.abs(randomizer.nextInt()) % NUMBER_OF_EASY_LAYOUTS + 1];
					break;
				case 2:
					temp = layouts[Math.abs(randomizer.nextInt()) % NUMBER_OF_MEDIUM_LAYOUTS + NUMBER_OF_EASY_LAYOUTS + 1];
					break;
				case 3:
					temp = layouts[Math.abs(randomizer.nextInt()) % NUMBER_OF_HARD_LAYOUTS + NUMBER_OF_MEDIUM_LAYOUTS + NUMBER_OF_EASY_LAYOUTS + 1];
					break;
				case 4:
					// final level
					break;
			}
			return new Room(temp.layout, temp.startingPosX, temp.startingPosY, randomizer);
		}
		public String[] getLayout() {
			return layout;
		}

		public int getStartingPosX() {
			return startingPosX;
		}

		public int getStartingPosY() {
			return startingPosY;
		}
	}
}