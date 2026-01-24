package net.ornithemc.keratin.api.manifest;

import com.vdurmont.semver4j.Semver;

import java.util.Objects;

public record VersionDetails(String id, String normalizedVersion, boolean client, boolean server, boolean sharedMappings, Downloads downloads) implements Comparable<VersionDetails> {

	public record Downloads(Download client, Download server, Download server_zip) {

		public record Download(String sha1, String url) {
		}
	}

	@Override
	public int compareTo(VersionDetails o) {
        return new Semver(Objects.requireNonNullElseGet(normalizedVersion, () -> normalize(id))).compareTo(new Semver(Objects.requireNonNullElseGet(o.normalizedVersion, () -> normalize(o.id))));
	}

	public int compareTo(String o) {
        return new Semver(Objects.requireNonNullElseGet(normalizedVersion, () -> normalize(id))).compareTo(new Semver(normalize(o)));
	}

	public static String normalize(String version) {
		if (Objects.equals(version, "15w14a")) return "1.8.4-april.fools";
		if (version.lastIndexOf('.') == 1) return version + ".0";

		return version;
	}
}
