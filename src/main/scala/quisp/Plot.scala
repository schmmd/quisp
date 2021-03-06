package quisp

/**
 * Created by rodneykinney on 4/14/15.
 */
object Plot extends quisp.flot.FlotChartDisplay with SeriesDataConversions {

  import quisp.flot._

  def toSeries(data: SeriesData) = {
    val pts = data.points.zipWithIndex.map {
      case (p, i) => (p.X, p.Y, p.Name) match {
        case (None, Some(y), None) => XYValue(i, y)
        case _ => p
      }
    }
    Vector(Series(pts))
  }

  def line(data: SeriesData) = {
    new FlotLineChart(FlotChart(toSeries(data),
      options = PlotOptions(series =
        DefaultSeriesOptions(lines =
          LineOptions()))), this)
  }

  def area(data: SeriesData) = {
    new FlotLineChart((FlotChart(toSeries(data),
      options = PlotOptions(series =
        DefaultSeriesOptions(lines =
          LineOptions(fill = Some(0.6)))))), this)
  }

  def column(data: SeriesData) = {
    new FlotBarChart(FlotChart(toSeries(data),
      options = PlotOptions(series =
        DefaultSeriesOptions(bars =
          BarOptions()))), this)
  }

  def bar(data: SeriesData) = {
    new FlotBarChart(FlotChart(toSeries(data),
      options = PlotOptions(series =
        DefaultSeriesOptions(bars =
          BarOptions(horizontal = Some(true))))), this)
  }

  def pie(data: SeriesData) = {
    val series = for (p <- data.points) yield {
      Series(data = List(YValue(p.Y.get)), label = p.Name.getOrElse(null))
    }
    new FlotPieChart(FlotChart(series.toIndexedSeq,
      options = PlotOptions(series =
        DefaultSeriesOptions(pie = PieOptions()))), this)
  }

  def scatter(data: SeriesData) = {
    new FlotLineChart(FlotChart(toSeries(data),
      options = PlotOptions(series =
        DefaultSeriesOptions(points = MarkerOptions()))), this)
  }

  def histogram(data: SeriesData, numBins: Int = 50) = {
    val config = FlotChart(Vector(Series(data.points)))
    new HistogramChart(config, this, numBins)
  }
}



