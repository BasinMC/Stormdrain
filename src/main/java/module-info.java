/**
 * Provides resources for interpreting data transmitted in GitHub web hooks.
 *
 * @author <a href="mailto:johannesd@torchmind.com">Johannes Donath</a>
 */
module org.basinmc.stormdrain {
  exports org.basinmc.stormdrain.event;
  exports org.basinmc.stormdrain.resource;

  requires static com.github.spotbugs.annotations;
  requires com.fasterxml.jackson.annotation;
  requires com.fasterxml.jackson.databind;
}
