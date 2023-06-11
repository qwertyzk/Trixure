package roguelike.resources;

import roguelike.logic.entities.Monster;
import roguelike.logic.level.Room;

import java.util.Random;
import java.util.Stack;

public class Layouts {

	public static final int NUMBER_OF_LAYOUTS = 20;

	public enum Layouts2
	{
		// # wall
		// . empty
		// s starting position
		// ^ exit
		// , trap
		// p potion
		// ! key
		// / locked door
		// T chest
		// M mob
		// $ shop (?)

		layout0(new String[]
				{"##########",
						"#........#",
						"#........#",
						"#...##...#",
						"#...##...#",
						"#...##...#",
						"#...##...#",
						"#...##...#",
						"##.####^##",
						"##########"}, 2, 8),

		layout1(new String[]
				{"##########################",
						"#....#..................!#",
						"#....#..........M........#",
						"#....#....M..........M...#",
						"#....#.........M.........#",
						"#p.......................#",
						"########################/#",
						"#............#...........#",
						"#....M.......#...M.......#",
						"#.#.....M....#...........#",
						"#.#..........#...........#",
						"#.#..M.......#.......M...#",
						"#.#......................#",
						"#.########################",
						"#......,,,.^.............#",
						"#....#########...........#",
						"#....................M...#",
						"#.....M..................#",
						"#.........M..............#",
						"#p......................T#",
						"##########################"}, 2, 2),

		layout2(new String[]
				{"##########################",
						"#........................#",
						"#######.################.#",
						"#.....#.#................#",
						"#.###.#.#.#####.########.#",
						"#.#!#.#.#.#.......##...#.#",
						"#.#...#.#.#.###.#....#M#.#",
						"#.#####.#.#..##.########.#",
						"#.......#.#####........#.#",
						"#.#.M.#.#.....#.#.....M#.#",
						"#.#####.#####.#.#M.....#.#",
						"#.......#.....#.#..M..T#.#",
						"##########/####.########.#",
						"#.M.,......#M............#",
						"#.,....,...###############",
						"#...M.p..,.#.............#",
						"#...,....M.#.###########,#",
						"#......,...#..........M#,#",
						"#.##########.#.#.#######.#",
						"#............#M#......^#T#",
						"##########################"}, 1, 1),

		layout3(new String[]
				{"##########################",
						"#............#^..M.......#",
						"#...M......#####.,.......#",
						"#..,.....,...#...,...M...#",
						"#.......,....#,......,...#",
						"#....M....,..#...M.,.....#",
						"#..,.........#..,........#",
						"#.....,..M...#,....,.M...#",
						"#...M.....,..#...M....,..#",
						"#............#..,........#",
						"#####....########....#####",
						"#p,.....,...#.........,.!#",
						"#.M..,......#..,M.,....M.#",
						"#,,.....M.,.#.......,....#",
						"#...,.......#...,.M......#",
						"#....M...,..#.......M....#",
						"#...,...M..###...,....,..#",
						"#..........#T#...........#",
						"#...########/#########...#",
						"#........................#",
						"##########################"}, 12, 1),

		layout4(new String[]
				{"##########################",
						"#.........M....,........p#",
						"#....M..,......,.........#",
						"#.......,.........M......#",
						"#/####################,,.#",
						"#....,....,........,p#...#",
						"#.....M...,..M...,.M.#..M#",
						"#....,...........,...#...#",
						"#/################,,.#...#",
						"#....,....,...M,p#...#M..#",
						"#.......M.,....,.#.,,#...#",
						"#....,...........#...#.,,#",
						"#/############,,.#.M.#...#",
						"#....,......p#M..#,,.#...#",
						"#....M...,...#...#...#...#",
						"#....,...,M..#.,,#...#.M.#",
						"#/########,,.#..M#.,,#...#",
						"#.......^#.M.#...#...#,,.#",
						"#........#...#...#.M.#...#",
						"#T.......#.!.#.!.#.!.#.!.#",
						"##########################"}, 1, 1),

		layout5(new String[]
				{"##########################",
						"#.^.#..................#p#",
						"#.,.#...M.....M........#/#",
						"#M..#....................#",
						"#.,.#....#####.#####...#,#",
						"#...#....#.........#...#p#",
						"#.,.#....#.........#...###",
						"#..M#....#..##.##..#...#p#",
						"#.,.#....#..#...#..#...#M#",
						"#...#....#M.#...#.M#...#.#",
						"#.,.#....#..#...#..#.....#",
						"#M..#....#..#####..#...###",
						"#.,.#....#....#....#...#p#",
						"#...#....#...!#!...#...#,#",
						"#.,.#....###########.....#",
						"#..M#....#...#...#T#...#/#",
						"#.,.#......#...#...#...#p#",
						"#........#################",
						"#.,.,...........M...,....#",
						"#M..............M...,...T#",
						"##########################"}, 14, 10),

		layout6(new String[]
				{"############",
						"#.#....#M#M#",
						"#.#.#M.#.#.#",
						"#.#.####.#.#",
						"#........#.#",
						"###.######.#",
						"#M#........#",
						"#.#.#.######",
						"#.#.#......#",
						"#.#.######.#",
						"#.......M#^#",
						"############"}, 1, 1),

		layout7(new String[]
				{"############",
						"#p.........#",
						"#...M......#",
						"#........###",
						"#.....M../T#",
						"#..#########",
						"#......M..!#",
						"#..M.......#",
						"#..........#",
						"#..........#",
						"#.....M...^#",
						"############"}, 10, 1),

		layout8(new String[]
				{"############",
						"#.........p#",
						"#...M...,..#",
						"#########..#",
						"#M......,..#",
						"#..,......M#",
						"#..#########",
						"#..#......M#",
						"#.,#..#,...#",
						"#.....#..###",
						"#p...M#...^#",
						"############"}, 1, 1),

		layout9(new String[]
				{"############",
						"#...#.!#..p#",
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
						"#^........M#",
						"#.##.......#",
						"#.##..##...#",
						"#.....##T..#",
						"#......M##.#",
						"#.M.##..##.#",
						"#...##M....#",
						"#......##..#",
						"#......##..#",
						"#.........M#",
						"############"}, 1, 10),

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
						"###########################################"}, 1, 4),

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

		public static Layouts2 randomType(int i)  {
			Layouts2[] layouts2 = values();
		return layouts2[(((i % NUMBER_OF_LAYOUTS) + 20) % NUMBER_OF_LAYOUTS) + 1];
		}

		public static Room ToRoom(Layouts2 l, Random randomiser)
		{
			return new Room(l.layout, l.startingPosX, l.startingPosY, randomiser);
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
