/**
 * Created by Spike
 * Created 25-01-2022 at 22:28
 */

package net.spike.api.Type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServerRule {

    VOICE_ENABLED("voiceEnabled"),
    MINIMAP_STATUS("minimapStatus"),
    SERVER_HANDLES_WAYPOINTS("serverHandlesWaypoints"),
    COMPETITIVE_GAMEMODE("competitiveGame");

    private final String name;

}
