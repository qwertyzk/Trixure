package resources;

import logic.level.Room;
import java.util.Random;

public class Layouts
{

	public static final int NUMBER_OF_EASY_LAYOUTS = 10;
	public static final int NUMBER_OF_MEDIUM_LAYOUTS = 8;
	public static final int NUMBER_OF_HARD_LAYOUTS = 8;

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
	// K princess
	// D dragon

	public enum Layouts2
	{
		layout0(new String[]
					   {"##########",
						"##!####^##",
						"#...##...#",
						"#...###/##",
						"#........#",
						"#........#",
						"#...##...#",
						"#...##...#",
						"##.####p##",
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
						"##########################"}, 14, 9),

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
					   {"##############",
						"#......./...W#",
						"######..######",
						"#.....M.#sGGG#",
						"#.......####.#",
						"#..M....,,,,,#",
						"#.......######",
						"#/###.M.....^#",
						"#..T#.....,,,#",
						"#.M.#..M..,..#",
						"#T.p#.....,.!#",
						"##############"}, 1, 1),

		layout12(new String[]
				 	   {"##############",
						"#...#mT...GG!#",
						"#...#......GG#",
						"#...#.M......#",
						"##.##....M...#",
						"#...#...M....#",
						"#M..#.M....M.#",
						"#...#...M....#",
						"#..M#p.......#",
						"#...####,,####",
						"#.M...M.....^#",
						"##############"}, 2, 2),

		layout13(new String[]
					   {"##############",
						"#...M...M...^#",
						"#.############",
						"#.,....MM../W#",
						"#.############",
						"#...M...M..,s#",
						"#.############",
						"#.....M...M,d#",
						"#.############",
						"#.,,,..M.../m#",
						"#.############",
						"#...M....M..!#",
						"##############"}, 1, 6),

		layout14(new String[]
					   {"##############",
						"####.....#.GG#",
						"##....M../..G#",
						"##.......#.Gs#",
						"#?.......#####",
						"#,,..M...#..?#",
						"#!,....../..T#",
						"#,,......#..?#",
						"#?..M....#####",
						"##.......#..d#",
						"##^....M./...#",
					    "####.....#..W#",
						"##############"}, 2, 2),

		layout15(new String[]
					   {"##############",
						"#.#GG.^#M...!#",
						"#.#G...#...M.#",
						"#.#....#.M...#",
						"#.#.M..#.....#",
						"#.#........M.#",
						"#....M.#######",
						"#/#........M.#",
						"#.#M...#M....#",
						"#.#....#...M.#",
						"#.#....#.M...#",
					    "#W#????#....m#",
						"##############"}, 1, 1),

		layout16(new String[]
					   {"######################",
						"#.....p#....M.......?#",
						"#......#.M....#......#",
						"#...M..#......#..M...#",
						"#......#...M..#......#",
						"#T............#?.....#",
					    "####################.#",
						"#!........M...#m,,...#",
						"#......#......#,,....#",
						"#......#..M...#,..M..#",
						"#......#....M.#.....G#",
						"#^.....#........M..GG#",
						"######################"}, 1, 1),

		layout17(new String[]
					   {"######################",
						"##p........M.....GG###",
						"#....M.............T##",
						"##......M......M.....#",
						"#################....#",
						"###....M.......###.M.#",
						"##..M..........^##...#",
						"#?........M....###M..#",
						"#?..#############....#",
						"#?...M.....M........M#",
						"##!............M...T##",
						"###.....M........GG###",
						"######################"}, 1, 2),

		layout18(new String[]
					   {"######################",
						"#...#p..M........#T.s#",
						"#.../........M.../..m#",
						"#...#.....M.....p#T.d#",
						"#...##################",
						"#....M.#...M.....#.GG#",
						"#......#G.....M..#...#",
						"#..M...####..........#",
						"#......#....M..#######",
						"###..............#...#",
						"#.M....M...#.....#.^.#",
						"#p.......GG#..M......#",
						"######################"}, 2, 2),

		layout19(new String[]
					   {"######################",
						"#.MM...T#GG..........#",
						"#.##############.###.#",
						"#.#.......M#M......#.#",
						"#.#.###.##########.#.#",
						"#...#....M#......#M#.#",
						"#.#M#.##########.#.#.#",
						"#.#.#.....^#.....#.#.#",
						"#M#.###########.##.#.#",
						"###.......#M.......#.#",
						"#?#####.############.#",
						"#....M..........M....#",
						"######################"}, 11, 5),

		layout20(new String[]
					   {"##############",
						"#.#......../s#",
						"#.#..M.....###",
						"#.M......M..G#",
						"#.....##....G#",
						"#,,,,####....#",
						"#,,,,####..M.#",
						"#..M..##p....#",
						"#..........GG#",
						"#.#.....M..###",
						"#^#......../d#",
						"##############"}, 1, 1),

		layout21(new String[]
					   {"############",
						"#......##^##",
						"#####..#.M.#",
						"#pM.#..#...#",
						"#.....M#.,,#",
						"#M..M..#.M.#",
						"#......#...#",
						"#.M#.GG#,,.#",
						"#..#####...#",
						"#........###",
						"#???#.GG./T#",
						"############"}, 1, 1),

		layout22(new String[]
					   {"############",
						"#.....,...p#",
						"#.,....M.,.#",
						"#...M.,....#",
						"#.M..,..M..#",
						"#..,..M,...#",
						"#...,....M.#",
						"#.M..M...,.#",
						"#.,...,.M..#",
						"#.,M.....,.#",
						"#!...,....^#",
						"############"}, 1, 1),

		layout23(new String[]
					   {"###############",
						"#d,,,.....G#G.#",
						"#####...#..#..#",
						"#s,,,.M.#.###.#",
						"#####...#..#..#",
						"#.......#M.#.M#",
						"#..M....#..#..#",
						"#.....M.#..M..#",
						"#M......#####.#",
						"#..M....#W.#..#",
						"#T.....^#../.p#",
						"###############"}, 13, 2),

		layout24(new String[]
					   {"############",
						"#...../.MTT#",
						"#...########",
						"#.....#..GG#",
						"####,.#...,#",
						"#p#...#.M..#",
						"#.#..M..,..#",
						"#.M.....#M.#",
						"#.....###..#",
						"#,###M.M##.#",
						"#!#m..M.#^.#",
						"############"}, 1, 1),

		layout25(new String[]
					   {"###############",
						"#.#M#M#...#...#",
						"#.#.#.#.#.#.#.#",
						"#.#...#.#M#M#.#",
						"#.#.#.#,#.#.#.#",
						"#M#.#...#...#M#",
						"#.#,#,#.#.#.#.#",
						"#...#.#.#.#.#.#",
						"#.#M#.#.#M#M#.#",
						"#.#.#M..#.#.#.#",
						"#M#.#.#,#.#...#",
					    "#.#.#.#...#,#M#",
					    "#...#.#.#,#.#.#",
					    "#.#,#M#M#...#.#",
					    "#.#.#.#.#M#.#.#",
					    "#p#...#...#p#^#",
						"###############"}, 1, 1),

		layout26(new String[]
					   {"##########################",
					    "#...##^##..,....M....#..p#",
					    "#...#.........,..........#",
					    "#...#GGG#..M......M,.#...#",
					    "##.####################.##",
					    "#...#GG?#..M#GTT#..M..,..#",
					    "#,M.#......./...#.,....M.#",
					    "#...#...#,..#GG.#........#",
					    "#.,M##.##...#####.M.##,..#",
					    "#...#M..#.M.#GGp#.,.#?.M.#",
					    "#M.,#...#..,#G..#...#!...#",
					    "#...#,.M#...#...#M..######",
					    "#.,.#...#M,.##.##...,....#",
					    "#.M.#..,#...,...#..M...M.#",
					    "#,..#M..#.M....M#..,.....#",
					    "##.##..,#####,..######,..#",
					    "#G..#...#Gd.#...#G..#....#",
					    "#.....,M#G../.M........M.#",
					    "#pG.#...#s..#...#.GG#....#",
					    "##########################"}, 2, 2),

		final_layout(new String[]
					   {"#######",
						"##.K.##",
						"##...##",
						"###D###",
						"##...##",
						"#.....#",
						"#.....#",
						"##...##",
						"###.###",
						"#######"}, 3, 8);



		private final String[] layout;
		private final int startingPosX;
		private final int startingPosY;

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
					temp = final_layout;
					break;
			}
			return new Room(temp.layout, temp.startingPosX, temp.startingPosY, randomizer, difficulty);
		}
	}
}
