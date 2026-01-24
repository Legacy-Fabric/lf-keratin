package net.ornithemc.keratin.api.manifest;

import java.util.Objects;

public record VersionDetails(String id, String normalizedVersion, boolean client, boolean server, boolean sharedMappings, Downloads downloads) implements Comparable<VersionDetails> {

	public record Downloads(Download client, Download server, Download server_zip) {

		public record Download(String sha1, String url) {
		}
	}

	@Override
	public int compareTo(VersionDetails o) {
        return normalize(Objects.requireNonNullElse(normalizedVersion, id)).compareTo(normalize(Objects.requireNonNullElse(o.normalizedVersion, o.id)));
	}

	public int compareTo(String o) {
        return normalize(Objects.requireNonNullElse(normalizedVersion, id)).compareTo(normalize(o));
	}

	public static net.fabricmc.loader.api.SemanticVersion normalize(String version) {
		return MCVersionLookup.parse(version);
	}
}
