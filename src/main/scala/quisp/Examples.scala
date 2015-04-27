package quisp

import quisp.flot.Corner

import scala.util.Random

import java.awt.Color

/**
 * Created by rodneykinney on 4/19/15.
 */
object Examples {


  object Highcharts {

    def cityTemperatures: Unit = {
      import quisp.Plot._
      line(List(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
        .xAxis.categories("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        .series(0).name("Tokyo")
        .addSeries(List(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5))
        .series(1).name("New York")
        .addSeries(List(-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0))
        .series(2).name("Berlin")
        .series(2).showPointLabels()
        .addSeries(List(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8))
        .series(3).name("London")
        .legend.layout(Orientation.vertical)
        .legend.verticalJustification(VAlign.middle)
        .legend.horizontalJustification(HAlign.right)
        .title.text("Monthly Average Temperatures")
        .yAxis.title.text("Temperature")
    }

    def populationGrowth: Unit = {
      import quisp.Plot._
      area(List(502, 635, 809, 947, 1402, 3634, 5268))
        .yAxis.title.text("Millions")
        .title.text("Worldwide population by Region")
        .xAxis.categories("1750", "1800", "1850", "1900", "1950", "1999", "2050")
        .series(0).name("Asia")
        .addSeries(List(106, 107, 111, 133, 221, 767, 1766))
        .series(1).name("Europe")
        .addSeries(List(163, 203, 276, 408, 547, 729, 628))
        .series(2).name("Africa")
        .addSeries(List(18, 31, 54, 156, 339, 818, 1201))
        .series(3).name("America")
        .addSeries(List(2, 2, 2, 6, 13, 30, 46))
        .series(4).name("Oceana")
        .defaultSettings.stacked
        .defaultSettings.lineWidth(1)
    }

    def bellCurve: Unit = {
      import quisp.Plot._
      val rand = new scala.util.Random()
      def randomSum = (0 until 10).map(i => rand.nextDouble).sum
      histogram((0 to 10000).map(i => randomSum))
    }

    def largestCities: Unit = {
      import quisp.Plot._
      import java.awt.Color
      import spray.json._
      import DefaultJsonProtocol._
      val data = List(
        ("Shanghai", 23.7),
        ("Lagos", 16.1),
        ("Instanbul", 14.2),
        ("Karachi", 14.0),
        ("Mumbai", 12.5),
        ("Moscow", 12.1),
        ("São Paulo", 11.8),
        ("Beijing", 11.7),
        ("Guangzhou", 11.1),
        ("Delhi", 11.1),
        ("Shenzhen", 10.5),
        ("Seoul", 10.4),
        ("Jakarta", 10.0),
        ("Kinshasa", 9.3),
        ("Tianjin", 9.3),
        ("Tokyo", 9.0),
        ("Cairo", 8.9),
        ("Dhaka", 8.9),
        ("Mexico City", 8.9),
        ("Lima", 8.9)
      )
      column(data)
        .title.text("World's largest cities")
        .legend.enabled(false)
        .xAxis.axisType(AxisType.category)
        .xAxis.additionalField("labels", Map("rotation" -> -45))
        .yAxis.title.text("Population")
        .series(0).showPointLabels(
          quisp.highcharts.PointLabelFormat(
            borderColor = Color.black,
            color = Color.WHITE,
            rotation = Some(-90),
            align = HAlign.right,
            x = Some(4),
            y = Some(3)
          ))
    }

    def electionResults: Unit = {
      import quisp.Plot._
      import java.awt.Color

      bar(List(210863, 498191, 234846))
        .title.text("Congressional Election Results")
        .layout.spacing(10, 25, 10, 10)
        .defaultSettings.stacking(Stacking.PERCENT)
        .xAxis.categories("Montana", "Oregon", "Ohio")
        .yAxis.title.text("Percent")
        .series(0).settings.color(Color.RED)
        .series(0).name("Republican")
        .addSeries(List(17712, 80214, 0))
        .series(1).name("Other")
        .series(1).settings.color(Color.GREEN)
        .addSeries(List(145601, 744516, 250722))
        .series(2).settings.color(Color.BLUE)
        .series(2).name("Democrat")
    }

    def browserShare: Unit = {
      import quisp.Plot._
      import spray.json.DefaultJsonProtocol._
      import spray.json._
      val data = List(
        ("Firefox", 45.0),
        ("IE", 26.8),
        ("Chrome", 12.8),
        ("Safari", 8.5),
        ("Opera", 6.2),
        ("Others", 0.7)
      )
      pie(data)
        .title.text("Browser market share")
        .series(0).showPointLabels(
          quisp.highcharts.PointLabelFormat(additionalFields =
            Map("format" -> "{point.name}: {point.percentage:.1f} %".toJson)))
    }

    def heightVsWeight: Unit = {
      import quisp.Plot._
      import java.awt.Color

      scatter(femaleData)
        .title.text("Height vs Weight by Gender")
        .xAxis.title.text("Height (cm)")
        .yAxis.title.text("Weight (kg)")
        .legend.layout(Orientation.VERTICAL)
        .legend.horizontalJustification(HAlign.LEFT)
        .legend.position(100, 80)
        .legend.floating(true)
        .legend.verticalJustification(VAlign.TOP)
        .series(0).name("Female")
        .series(0).settings.color(new Color(223, 83, 83, 128))
        .addSeries(maleData)
        .series(1).name("Male")
        .series(1).settings.color(new Color(119, 152, 191, 128))
    }

    def examples: Unit = {
      quisp.Plot.columns(3)
      cityTemperatures
      populationGrowth
      largestCities
      electionResults
      browserShare
      heightVsWeight
    }

    def exerciseImplicitConversions: Unit = {
      import quisp.Plot._

      line(1 to 10)
      line(List(1, 2, 3, 4))
      line(Array(1, 2, 3, 4))

      line((1 to 10).map(_.toDouble))
      line(List(1.0, 2.0, 3.0, 4.0))
      line(Array(1.0, 2.0, 3.0, 4.0))

      line(1 to 10, 2 to 20)
      line(List(1, 2, 3, 4), List(1, 2, 3, 4))
      line(Array(1, 2, 3, 4), Array(1, 2, 3, 4))
      line(1 to 10, (2 to 20).map(_.toDouble))
      line(Array(1, 2, 3, 4), Array(1.0, 2.0, 3.0, 4.0))
      line((1 to 10).map(_.toDouble), 2 to 20)
      line((1 to 10).map(_.toDouble), (2 to 20).map(_.toDouble))
      line(Array(1.0, 2.0, 3.0, 4.0), Array(1.0, 2.0, 3.0, 4.0))

      line((1 to 10).map(i => (i, i * i)))
      line(List((1, 2), (3, 4)))
      line(List((1, 2.0), (3, 4.0)))
      line(List((1, "A"), (2, "B")))
      line(List(("A", 1), ("B", 2)))

      def sq(x: Double): Double = x * x
      val sqf = sq _
      line(1 to 10, sqf)
      line(List(1, 2, 3, 4), sqf)
      line(Array(1, 2, 3, 4), sqf)
      line(List(1.0, 2.0, 3.0, 4.0), sqf)
      line(Array(1.0, 2.0, 3.0, 4.0), sqf)
      line(sqf, 1 to 10)
      line(sqf, List(1, 2, 3, 4))
      line(sqf, List(1.0, 2.0, 3.0, 4.0))
      line(sqf, Array(1, 2, 3, 4))
      line(sqf, Array(1.0, 2.0, 3.0, 4.0))
      line(1 to 10, (x: Double) => x * x)

      val labels = "ABCDEFGHIJ".map(_.toString)
      line(labels)
      line(1 to 10, labels)
      line(List(1, 2, 3, 4), labels)
      line(Array(1, 2, 3, 4), labels)
      line(List(1.0, 2.0, 3.0, 4.0), labels)
      line(Array(1.0, 2.0, 3.0, 4.0), labels)
      line(labels, 1 to 10)
      line(labels, List(1, 2, 3, 4))
      line(labels, Array(1, 2, 3, 4))
      line(labels, List(1.0, 2.0, 3.0, 4.0))
      line(labels, Array(1.0, 2.0, 3.0, 4.0))
      line(labels.zip((1 to 10)))
      line((1 to 10).zip(labels))

      val labelArray = "ABCDEFGHIJ".map(_.toString).toArray
      line(labelArray)
      line(1 to 10, labelArray)
      line(List(1, 2, 3, 4), labelArray)
      line(Array(1, 2, 3, 4), labelArray)
      line(List(1.0, 2.0, 3.0, 4.0), labelArray)
      line(Array(1.0, 2.0, 3.0, 4.0), labelArray)
      line(labelArray, 1 to 10)
      line(labelArray, List(1, 2, 3, 4))
      line(labelArray, Array(1, 2, 3, 4))
      line(labelArray, List(1.0, 2.0, 3.0, 4.0))
      line(labelArray, Array(1.0, 2.0, 3.0, 4.0))
      line(labelArray.zip((1 to 10)))
      line((1 to 10).zip(labelArray))
    }
  }

  object Flot {

    import quisp.Plot.Flot._

    def examples: Unit = {
      columns(3)
      cityTemperatures
      populationGrowth
      largestCities
      electionResults
      browserShare
      heightVsWeight
    }

    def scratch: Unit = {
      line(1 to 10)
    }

    def cityTemperatures: Unit = {
      import quisp.Plot.Flot._
      import quisp.flot._
      val months =
        List("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
      line(months, List(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6))
        .title("Monthly Average Temperatures")
        .legend.enabled(true)
        .yAxis.label("Temperature")
        .series(0).name("Tokyo")
        .series(0).markerOptions.symbol(Symbol.circle)
        .xAxis.categorical(true)
        .addSeries(months, List(-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5))
        .series(1).name("New York")
        .series(1).markerOptions.symbol(Symbol.square)
        .addSeries(months, List(-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0))
        .series(2).name("Berlin")
        .series(2).markerOptions.symbol(Symbol.diamond)
        .addSeries(months, List(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8))
        .series(3).name("London")
        .series(3).markerOptions.symbol(Symbol.cross)
    }

    def populationGrowth: Unit = {
      import quisp.Plot.Flot._
      val years = List("1750", "1800", "1850", "1900", "1950", "1999", "2050")
      area(years, List(2, 2, 2, 6, 13, 30, 46))
        .yAxis.label("Millions")
        .stacked(true)
        .legend.position(Corner.nw)
        .legend.columns(5)
        .title("Worldwide population by Region")
        .xAxis.categorical(true)
        .series(0).name("Oceana")
        .addSeries(years, List(18, 31, 54, 156, 339, 818, 1201))
        .series(1).name("America")
        .addSeries(years, List(163, 203, 276, 408, 547, 729, 628))
        .series(2).name("Africa")
        .addSeries(years, List(106, 107, 111, 133, 221, 767, 1766))
        .series(3).name("Europe")
        .addSeries(years, List(502, 635, 809, 947, 1402, 3634, 5268))
        .series(4).name("Asia")
    }

    def largestCities: Unit = {
      import quisp.Plot.Flot._
      import java.awt.Color
      val data = List(
        ("Shanghai", 23.7),
        ("Lagos", 16.1),
        ("Instanbul", 14.2),
        ("Karachi", 14.0),
        ("Mumbai", 12.5),
        ("Moscow", 12.1),
        ("São Paulo", 11.8),
        ("Beijing", 11.7),
        ("Guangzhou", 11.1),
        ("Delhi", 11.1),
        ("Shenzhen", 10.5),
        ("Seoul", 10.4),
        ("Jakarta", 10.0),
        ("Kinshasa", 9.3),
        ("Tianjin", 9.3),
        ("Tokyo", 9.0),
        ("Cairo", 8.9),
        ("Dhaka", 8.9),
        ("Mexico City", 8.9),
        ("Lima", 8.9)
      )
      column(data)
        .title("World's largest cities")
        .legend.enabled(false)
        .xAxis.categorical(true)
        .yAxis.label("Population")
        .options.fractionalBarWidth(.7)
    }

    def electionResults: Unit = {
      import quisp.Plot.Flot._
      import java.awt.Color

      val states = List("Montana", "Oregon", "Ohio")
      column(states, List(0.563, 0.376, 0.483))
        .title("Congressional Election Results")
        .legend.enabled(true)
        .yAxis.range(0, 1)
        .options.fractionalBarWidth(0.6)
        .options.fillOpacity(1.0)
        .stacked(true)
        .xAxis.categorical(true)
        .series(0).color(Color.RED)
        .series(0).name("Republican")
        .addSeries(states, List(0.047, 0.060, 0.0))
        .series(1).name("Other")
        .series(1).color(Color.GREEN)
        .addSeries(states, List(0.389, 0.562, 0.516))
        .series(2).color(Color.BLUE)
        .series(2).name("Democrat")
    }

    def browserShare: Unit = {
      import quisp.Plot.Flot._
      val data = List(
        ("Firefox", 45.0),
        ("IE", 26.8),
        ("Chrome", 12.8),
        ("Safari", 8.5),
        ("Opera", 6.2),
        ("Others", 0.7)
      )
      pie(data)
        .title("Browser market share")
        .options.radius(0.8)
    }

    def heightVsWeight: Unit = {
      import quisp.Plot.Flot._
      import java.awt.Color

      val fColor = new Color(223, 83, 83, 128)
      val mColor = new Color(119, 152, 191, 128)
      scatter(femaleData)
        .title("Height vs Weight by Gender")
        .xAxis.label("Height (cm)")
        .yAxis.label("Weight (kg)")
        .series(0).name("Female")
        .series(0).color(fColor)
        .series(0).markerOptions.fillColor(fColor)
        .addSeries(maleData)
        .series(1).name("Male")
        .series(1).color(mColor)
        .series(1).markerOptions.fillColor(mColor)
    }


  }

  def main(args: Array[String]): Unit = {
    //            Highcharts.examples
    Flot.examples
  }

  val femaleData =
    List((161.2, 51.6), (167.5, 59.0), (159.5, 49.2), (157.0, 63.0), (155.8, 53.6),
      (170.0, 59.0), (159.1, 47.6), (166.0, 69.8), (176.2, 66.8), (160.2, 75.2),
      (172.5, 55.2), (170.9, 54.2), (172.9, 62.5), (153.4, 42.0), (160.0, 50.0),
      (147.2, 49.8), (168.2, 49.2), (175.0, 73.2), (157.0, 47.8), (167.6, 68.8),
      (159.5, 50.6), (175.0, 82.5), (166.8, 57.2), (176.5, 87.8), (170.2, 72.8),
      (174.0, 54.5), (173.0, 59.8), (179.9, 67.3), (170.5, 67.8), (160.0, 47.0),
      (154.4, 46.2), (162.0, 55.0), (176.5, 83.0), (160.0, 54.4), (152.0, 45.8),
      (162.1, 53.6), (170.0, 73.2), (160.2, 52.1), (161.3, 67.9), (166.4, 56.6),
      (168.9, 62.3), (163.8, 58.5), (167.6, 54.5), (160.0, 50.2), (161.3, 60.3),
      (167.6, 58.3), (165.1, 56.2), (160.0, 50.2), (170.0, 72.9), (157.5, 59.8),
      (167.6, 61.0), (160.7, 69.1), (163.2, 55.9), (152.4, 46.5), (157.5, 54.3),
      (168.3, 54.8), (180.3, 60.7), (165.5, 60.0), (165.0, 62.0), (164.5, 60.3),
      (156.0, 52.7), (160.0, 74.3), (163.0, 62.0), (165.7, 73.1), (161.0, 80.0),
      (162.0, 54.7), (166.0, 53.2), (174.0, 75.7), (172.7, 61.1), (167.6, 55.7),
      (151.1, 48.7), (164.5, 52.3), (163.5, 50.0), (152.0, 59.3), (169.0, 62.5),
      (164.0, 55.7), (161.2, 54.8), (155.0, 45.9), (170.0, 70.6), (176.2, 67.2),
      (170.0, 69.4), (162.5, 58.2), (170.3, 64.8), (164.1, 71.6), (169.5, 52.8),
      (163.2, 59.8), (154.5, 49.0), (159.8, 50.0), (173.2, 69.2), (170.0, 55.9),
      (161.4, 63.4), (169.0, 58.2), (166.2, 58.6), (159.4, 45.7), (162.5, 52.2),
      (159.0, 48.6), (162.8, 57.8), (159.0, 55.6), (179.8, 66.8), (162.9, 59.4),
      (161.0, 53.6), (151.1, 73.2), (168.2, 53.4), (168.9, 69.0), (173.2, 58.4),
      (171.8, 56.2), (178.0, 70.6), (164.3, 59.8), (163.0, 72.0), (168.5, 65.2),
      (166.8, 56.6), (172.7, 105.2), (163.5, 51.8), (169.4, 63.4), (167.8, 59.0),
      (159.5, 47.6), (167.6, 63.0), (161.2, 55.2), (160.0, 45.0), (163.2, 54.0),
      (162.2, 50.2), (161.3, 60.2), (149.5, 44.8), (157.5, 58.8), (163.2, 56.4),
      (172.7, 62.0), (155.0, 49.2), (156.5, 67.2), (164.0, 53.8), (160.9, 54.4),
      (162.8, 58.0), (167.0, 59.8), (160.0, 54.8), (160.0, 43.2), (168.9, 60.5),
      (158.2, 46.4), (156.0, 64.4), (160.0, 48.8), (167.1, 62.2), (158.0, 55.5),
      (167.6, 57.8), (156.0, 54.6), (162.1, 59.2), (173.4, 52.7), (159.8, 53.2),
      (170.5, 64.5), (159.2, 51.8), (157.5, 56.0), (161.3, 63.6), (162.6, 63.2),
      (160.0, 59.5), (168.9, 56.8), (165.1, 64.1), (162.6, 50.0), (165.1, 72.3),
      (166.4, 55.0), (160.0, 55.9), (152.4, 60.4), (170.2, 69.1), (162.6, 84.5),
      (170.2, 55.9), (158.8, 55.5), (172.7, 69.5), (167.6, 76.4), (162.6, 61.4),
      (167.6, 65.9), (156.2, 58.6), (175.2, 66.8), (172.1, 56.6), (162.6, 58.6),
      (160.0, 55.9), (165.1, 59.1), (182.9, 81.8), (166.4, 70.7), (165.1, 56.8),
      (177.8, 60.0), (165.1, 58.2), (175.3, 72.7), (154.9, 54.1), (158.8, 49.1),
      (172.7, 75.9), (168.9, 55.0), (161.3, 57.3), (167.6, 55.0), (165.1, 65.5),
      (175.3, 65.5), (157.5, 48.6), (163.8, 58.6), (167.6, 63.6), (165.1, 55.2),
      (165.1, 62.7), (168.9, 56.6), (162.6, 53.9), (164.5, 63.2), (176.5, 73.6),
      (168.9, 62.0), (175.3, 63.6), (159.4, 53.2), (160.0, 53.4), (170.2, 55.0),
      (162.6, 70.5), (167.6, 54.5), (162.6, 54.5), (160.7, 55.9), (160.0, 59.0),
      (157.5, 63.6), (162.6, 54.5), (152.4, 47.3), (170.2, 67.7), (165.1, 80.9),
      (172.7, 70.5), (165.1, 60.9), (170.2, 63.6), (170.2, 54.5), (170.2, 59.1),
      (161.3, 70.5), (167.6, 52.7), (167.6, 62.7), (165.1, 86.3), (162.6, 66.4),
      (152.4, 67.3), (168.9, 63.0), (170.2, 73.6), (175.2, 62.3), (175.2, 57.7),
      (160.0, 55.4), (165.1, 104.1), (174.0, 55.5), (170.2, 77.3), (160.0, 80.5),
      (167.6, 64.5), (167.6, 72.3), (167.6, 61.4), (154.9, 58.2), (162.6, 81.8),
      (175.3, 63.6), (171.4, 53.4), (157.5, 54.5), (165.1, 53.6), (160.0, 60.0),
      (174.0, 73.6), (162.6, 61.4), (174.0, 55.5), (162.6, 63.6), (161.3, 60.9),
      (156.2, 60.0), (149.9, 46.8), (169.5, 57.3), (160.0, 64.1), (175.3, 63.6),
      (169.5, 67.3), (160.0, 75.5), (172.7, 68.2), (162.6, 61.4), (157.5, 76.8),
      (176.5, 71.8), (164.4, 55.5), (160.7, 48.6), (174.0, 66.4), (163.8, 67.3))

  val maleData = List((174.0, 65.6), (175.3, 71.8), (193.5, 80.7), (186.5, 72.6), (187.2, 78.8),
    (181.5, 74.8), (184.0, 86.4), (184.5, 78.4), (175.0, 62.0), (184.0, 81.6),
    (180.0, 76.6), (177.8, 83.6), (192.0, 90.0), (176.0, 74.6), (174.0, 71.0),
    (184.0, 79.6), (192.7, 93.8), (171.5, 70.0), (173.0, 72.4), (176.0, 85.9),
    (176.0, 78.8), (180.5, 77.8), (172.7, 66.2), (176.0, 86.4), (173.5, 81.8),
    (178.0, 89.6), (180.3, 82.8), (180.3, 76.4), (164.5, 63.2), (173.0, 60.9),
    (183.5, 74.8), (175.5, 70.0), (188.0, 72.4), (189.2, 84.1), (172.8, 69.1),
    (170.0, 59.5), (182.0, 67.2), (170.0, 61.3), (177.8, 68.6), (184.2, 80.1),
    (186.7, 87.8), (171.4, 84.7), (172.7, 73.4), (175.3, 72.1), (180.3, 82.6),
    (182.9, 88.7), (188.0, 84.1), (177.2, 94.1), (172.1, 74.9), (167.0, 59.1),
    (169.5, 75.6), (174.0, 86.2), (172.7, 75.3), (182.2, 87.1), (164.1, 55.2),
    (163.0, 57.0), (171.5, 61.4), (184.2, 76.8), (174.0, 86.8), (174.0, 72.2),
    (177.0, 71.6), (186.0, 84.8), (167.0, 68.2), (171.8, 66.1), (182.0, 72.0),
    (167.0, 64.6), (177.8, 74.8), (164.5, 70.0), (192.0, 101.6), (175.5, 63.2),
    (171.2, 79.1), (181.6, 78.9), (167.4, 67.7), (181.1, 66.0), (177.0, 68.2),
    (174.5, 63.9), (177.5, 72.0), (170.5, 56.8), (182.4, 74.5), (197.1, 90.9),
    (180.1, 93.0), (175.5, 80.9), (180.6, 72.7), (184.4, 68.0), (175.5, 70.9),
    (180.6, 72.5), (177.0, 72.5), (177.1, 83.4), (181.6, 75.5), (176.5, 73.0),
    (175.0, 70.2), (174.0, 73.4), (165.1, 70.5), (177.0, 68.9), (192.0, 102.3),
    (176.5, 68.4), (169.4, 65.9), (182.1, 75.7), (179.8, 84.5), (175.3, 87.7),
    (184.9, 86.4), (177.3, 73.2), (167.4, 53.9), (178.1, 72.0), (168.9, 55.5),
    (157.2, 58.4), (180.3, 83.2), (170.2, 72.7), (177.8, 64.1), (172.7, 72.3),
    (165.1, 65.0), (186.7, 86.4), (165.1, 65.0), (174.0, 88.6), (175.3, 84.1),
    (185.4, 66.8), (177.8, 75.5), (180.3, 93.2), (180.3, 82.7), (177.8, 58.0),
    (177.8, 79.5), (177.8, 78.6), (177.8, 71.8), (177.8, 116.4), (163.8, 72.2),
    (188.0, 83.6), (198.1, 85.5), (175.3, 90.9), (166.4, 85.9), (190.5, 89.1),
    (166.4, 75.0), (177.8, 77.7), (179.7, 86.4), (172.7, 90.9), (190.5, 73.6),
    (185.4, 76.4), (168.9, 69.1), (167.6, 84.5), (175.3, 64.5), (170.2, 69.1),
    (190.5, 108.6), (177.8, 86.4), (190.5, 80.9), (177.8, 87.7), (184.2, 94.5),
    (176.5, 80.2), (177.8, 72.0), (180.3, 71.4), (171.4, 72.7), (172.7, 84.1),
    (172.7, 76.8), (177.8, 63.6), (177.8, 80.9), (182.9, 80.9), (170.2, 85.5),
    (167.6, 68.6), (175.3, 67.7), (165.1, 66.4), (185.4, 102.3), (181.6, 70.5),
    (172.7, 95.9), (190.5, 84.1), (179.1, 87.3), (175.3, 71.8), (170.2, 65.9),
    (193.0, 95.9), (171.4, 91.4), (177.8, 81.8), (177.8, 96.8), (167.6, 69.1),
    (167.6, 82.7), (180.3, 75.5), (182.9, 79.5), (176.5, 73.6), (186.7, 91.8),
    (188.0, 84.1), (188.0, 85.9), (177.8, 81.8), (174.0, 82.5), (177.8, 80.5),
    (171.4, 70.0), (185.4, 81.8), (185.4, 84.1), (188.0, 90.5), (188.0, 91.4),
    (182.9, 89.1), (176.5, 85.0), (175.3, 69.1), (175.3, 73.6), (188.0, 80.5),
    (188.0, 82.7), (175.3, 86.4), (170.5, 67.7), (179.1, 92.7), (177.8, 93.6),
    (175.3, 70.9), (182.9, 75.0), (170.8, 93.2), (188.0, 93.2), (180.3, 77.7),
    (177.8, 61.4), (185.4, 94.1), (168.9, 75.0), (185.4, 83.6), (180.3, 85.5),
    (174.0, 73.9), (167.6, 66.8), (182.9, 87.3), (160.0, 72.3), (180.3, 88.6),
    (167.6, 75.5), (186.7, 101.4), (175.3, 91.1), (175.3, 67.3), (175.9, 77.7),
    (175.3, 81.8), (179.1, 75.5), (181.6, 84.5), (177.8, 76.6), (182.9, 85.0),
    (177.8, 102.5), (184.2, 77.3), (179.1, 71.8), (176.5, 87.9), (188.0, 94.3),
    (174.0, 70.9), (167.6, 64.5), (170.2, 77.3), (167.6, 72.3), (188.0, 87.3),
    (174.0, 80.0), (176.5, 82.3), (180.3, 73.6), (167.6, 74.1), (188.0, 85.9),
    (180.3, 73.2), (167.6, 76.3), (183.0, 65.9), (183.0, 90.9), (179.1, 89.1),
    (170.2, 62.3), (177.8, 82.7), (179.1, 79.1), (190.5, 98.2), (177.8, 84.1),
    (180.3, 83.2), (180.3, 83.2))

}
