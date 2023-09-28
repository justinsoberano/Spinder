package mappers;

import mappers.Artist.Artist;
import mappers.Artist.ArtistMapper;
import mappers.Track.Track;
import mappers.Track.TrackMapper;

import java.util.List;

public class Test {

    public static void main(String[] args) {

        /* Example data returned from Spotify */
        String artistData = "[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/06HL4z0CvFAxyc27GXpf02\"}},\"followers\":{\"href\":null,\"total\":0},\"genres\":[\"pop\"],\"href\":\"https://api.spotify.com/v1/artists/06HL4z0CvFAxyc27GXpf02\",\"id\":\"06HL4z0CvFAxyc27GXpf02\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab6761610000e5eb6a224073987b930f99adc706\",\"width\":640},{\"height\":320,\"url\":\"https://i.scdn.co/image/ab676161000051746a224073987b930f99adc706\",\"width\":320},{\"height\":160,\"url\":\"https://i.scdn.co/image/ab6761610000f1786a224073987b930f99adc706\",\"width\":160}],\"name\":\"Taylor Swift\",\"popularity\":100,\"type\":\"ARTIST\",\"uri\":\"spotify:artist:06HL4z0CvFAxyc27GXpf02\"},{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/4uFZsG1vXrPcvnZ4iSQyrx\"}},\"followers\":{\"href\":null,\"total\":0},\"genres\":[\"pixel\"],\"href\":\"https://api.spotify.com/v1/artists/4uFZsG1vXrPcvnZ4iSQyrx\",\"id\":\"4uFZsG1vXrPcvnZ4iSQyrx\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab6761610000e5eba9b8234e3071836212561d19\",\"width\":640},{\"height\":320,\"url\":\"https://i.scdn.co/image/ab67616100005174a9b8234e3071836212561d19\",\"width\":320},{\"height\":160,\"url\":\"https://i.scdn.co/image/ab6761610000f178a9b8234e3071836212561d19\",\"width\":160}],\"name\":\"C418\",\"popularity\":70,\"type\":\"ARTIST\",\"uri\":\"spotify:artist:4uFZsG1vXrPcvnZ4iSQyrx\"},{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/1Xyo4u8uXC1ZmMpatF05PJ\"}},\"followers\":{\"href\":null,\"total\":0},\"genres\":[\"canadian contemporary r&b\",\"canadian pop\",\"pop\"],\"href\":\"https://api.spotify.com/v1/artists/1Xyo4u8uXC1ZmMpatF05PJ\",\"id\":\"1Xyo4u8uXC1ZmMpatF05PJ\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab6761610000e5eb214f3cf1cbe7139c1e26ffbb\",\"width\":640},{\"height\":320,\"url\":\"https://i.scdn.co/image/ab67616100005174214f3cf1cbe7139c1e26ffbb\",\"width\":320},{\"height\":160,\"url\":\"https://i.scdn.co/image/ab6761610000f178214f3cf1cbe7139c1e26ffbb\",\"width\":160}],\"name\":\"The Weeknd\",\"popularity\":94,\"type\":\"ARTIST\",\"uri\":\"spotify:artist:1Xyo4u8uXC1ZmMpatF05PJ\"},{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/77SW9BnxLY8rJ0RciFqkHh\"}},\"followers\":{\"href\":null,\"total\":0},\"genres\":[\"modern alternative rock\",\"modern rock\",\"pop\"],\"href\":\"https://api.spotify.com/v1/artists/77SW9BnxLY8rJ0RciFqkHh\",\"id\":\"77SW9BnxLY8rJ0RciFqkHh\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab6761610000e5ebdf0b5ac84376a0a4b2166816\",\"width\":640},{\"height\":320,\"url\":\"https://i.scdn.co/image/ab67616100005174df0b5ac84376a0a4b2166816\",\"width\":320},{\"height\":160,\"url\":\"https://i.scdn.co/image/ab6761610000f178df0b5ac84376a0a4b2166816\",\"width\":160}],\"name\":\"The Neighbourhood\",\"popularity\":82,\"type\":\"ARTIST\",\"uri\":\"spotify:artist:77SW9BnxLY8rJ0RciFqkHh\"},{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/7tYKF4w9nC0nq9CsPZTHyP\"}},\"followers\":{\"href\":null,\"total\":0},\"genres\":[\"pop\",\"r&b\",\"rap\"],\"href\":\"https://api.spotify.com/v1/artists/7tYKF4w9nC0nq9CsPZTHyP\",\"id\":\"7tYKF4w9nC0nq9CsPZTHyP\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab6761610000e5eb7eb7f6371aad8e67e01f0a03\",\"width\":640},{\"height\":320,\"url\":\"https://i.scdn.co/image/ab676161000051747eb7f6371aad8e67e01f0a03\",\"width\":320},{\"height\":160,\"url\":\"https://i.scdn.co/image/ab6761610000f1787eb7f6371aad8e67e01f0a03\",\"width\":160}],\"name\":\"SZA\",\"popularity\":89,\"type\":\"ARTIST\",\"uri\":\"spotify:artist:7tYKF4w9nC0nq9CsPZTHyP\"}]";

        List<Artist> artists = ArtistMapper.artistData(artistData);

        for(Artist a : artists) {
            System.out.println(a.getName());
            System.out.println(a.getId() + "\n");
        }

        /* Example data returned from Spotify */
        String trackData = "[{\"album\":{\"albumGroup\":null,\"albumType\":\"ALBUM\",\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/6kic5bCjlohhDn9KzXbOta\"}},\"href\":\"https://api.spotify.com/v1/artists/6kic5bCjlohhDn9KzXbOta\",\"id\":\"6kic5bCjlohhDn9KzXbOta\",\"name\":\"Clark\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:6kic5bCjlohhDn9KzXbOta\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/album/0URaEmyTyvybd3lnbKnA3f\"}},\"href\":\"https://api.spotify.com/v1/albums/0URaEmyTyvybd3lnbKnA3f\",\"id\":\"0URaEmyTyvybd3lnbKnA3f\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab67616d0000b273ee901996011bae75d8852c33\",\"width\":640},{\"height\":300,\"url\":\"https://i.scdn.co/image/ab67616d00001e02ee901996011bae75d8852c33\",\"width\":300},{\"height\":64,\"url\":\"https://i.scdn.co/image/ab67616d00004851ee901996011bae75d8852c33\",\"width\":64}],\"name\":\"Sus Dog\",\"releaseDate\":\"2023-05-26\",\"releaseDatePrecision\":\"DAY\",\"restrictions\":null,\"type\":\"ALBUM\",\"uri\":\"spotify:album:0URaEmyTyvybd3lnbKnA3f\"},\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/6kic5bCjlohhDn9KzXbOta\"}},\"href\":\"https://api.spotify.com/v1/artists/6kic5bCjlohhDn9KzXbOta\",\"id\":\"6kic5bCjlohhDn9KzXbOta\",\"name\":\"Clark\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:6kic5bCjlohhDn9KzXbOta\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"discNumber\":1,\"durationMs\":159250,\"externalIds\":{\"externalIds\":{\"isrc\":\"UKQ7N2200008\"}},\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/track/3fq4PGd0wr098IqEBUrM2n\"}},\"href\":\"https://api.spotify.com/v1/tracks/3fq4PGd0wr098IqEBUrM2n\",\"id\":\"3fq4PGd0wr098IqEBUrM2n\",\"isPlayable\":null,\"linkedFrom\":null,\"restrictions\":null,\"name\":\"Dolgoch Tape\",\"popularity\":36,\"previewUrl\":\"https://p.scdn.co/mp3-preview/d402a45586739204fb696d57053d3d6bac9c5c4b?cid=ae02bde4d6ef4bc395502d8f76e38f04\",\"trackNumber\":8,\"type\":\"TRACK\",\"uri\":\"spotify:track:3fq4PGd0wr098IqEBUrM2n\",\"isExplicit\":false},{\"album\":{\"albumGroup\":null,\"albumType\":\"SINGLE\",\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/7GtPtPbDC5CLNzSNxVpqaf\"}},\"href\":\"https://api.spotify.com/v1/artists/7GtPtPbDC5CLNzSNxVpqaf\",\"id\":\"7GtPtPbDC5CLNzSNxVpqaf\",\"name\":\"Britney Manson\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:7GtPtPbDC5CLNzSNxVpqaf\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/album/7umRaDZ9FqTaL2EWZvGMEX\"}},\"href\":\"https://api.spotify.com/v1/albums/7umRaDZ9FqTaL2EWZvGMEX\",\"id\":\"7umRaDZ9FqTaL2EWZvGMEX\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab67616d0000b27306931432244da0dfcda6377d\",\"width\":640},{\"height\":300,\"url\":\"https://i.scdn.co/image/ab67616d00001e0206931432244da0dfcda6377d\",\"width\":300},{\"height\":64,\"url\":\"https://i.scdn.co/image/ab67616d0000485106931432244da0dfcda6377d\",\"width\":64}],\"name\":\"FÎ›SHION\",\"releaseDate\":\"2023-08-11\",\"releaseDatePrecision\":\"DAY\",\"restrictions\":null,\"type\":\"ALBUM\",\"uri\":\"spotify:album:7umRaDZ9FqTaL2EWZvGMEX\"},\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/7GtPtPbDC5CLNzSNxVpqaf\"}},\"href\":\"https://api.spotify.com/v1/artists/7GtPtPbDC5CLNzSNxVpqaf\",\"id\":\"7GtPtPbDC5CLNzSNxVpqaf\",\"name\":\"Britney Manson\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:7GtPtPbDC5CLNzSNxVpqaf\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"discNumber\":1,\"durationMs\":146531,\"externalIds\":{\"externalIds\":{\"isrc\":\"RUB862300027\"}},\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/track/4YQgOgIElFtEvwJQ9ArWL7\"}},\"href\":\"https://api.spotify.com/v1/tracks/4YQgOgIElFtEvwJQ9ArWL7\",\"id\":\"4YQgOgIElFtEvwJQ9ArWL7\",\"isPlayable\":null,\"linkedFrom\":null,\"restrictions\":null,\"name\":\"FÎ›SHION\",\"popularity\":77,\"previewUrl\":\"https://p.scdn.co/mp3-preview/ccd5e16b4b645437b80e3d320c330da308b42476?cid=ae02bde4d6ef4bc395502d8f76e38f04\",\"trackNumber\":1,\"type\":\"TRACK\",\"uri\":\"spotify:track:4YQgOgIElFtEvwJQ9ArWL7\",\"isExplicit\":true},{\"album\":{\"albumGroup\":null,\"albumType\":\"ALBUM\",\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/39vA9YljbnOApXKniLWBZv\"}},\"href\":\"https://api.spotify.com/v1/artists/39vA9YljbnOApXKniLWBZv\",\"id\":\"39vA9YljbnOApXKniLWBZv\",\"name\":\"Sylvan Esso\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:39vA9YljbnOApXKniLWBZv\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/album/2KAPJ40On8JY1Yt64JJgTk\"}},\"href\":\"https://api.spotify.com/v1/albums/2KAPJ40On8JY1Yt64JJgTk\",\"id\":\"2KAPJ40On8JY1Yt64JJgTk\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab67616d0000b273bffb48cb7afb996ecefdcfba\",\"width\":640},{\"height\":300,\"url\":\"https://i.scdn.co/image/ab67616d00001e02bffb48cb7afb996ecefdcfba\",\"width\":300},{\"height\":64,\"url\":\"https://i.scdn.co/image/ab67616d00004851bffb48cb7afb996ecefdcfba\",\"width\":64}],\"name\":\"Free Love\",\"releaseDate\":\"2020-09-25\",\"releaseDatePrecision\":\"DAY\",\"restrictions\":null,\"type\":\"ALBUM\",\"uri\":\"spotify:album:2KAPJ40On8JY1Yt64JJgTk\"},\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/39vA9YljbnOApXKniLWBZv\"}},\"href\":\"https://api.spotify.com/v1/artists/39vA9YljbnOApXKniLWBZv\",\"id\":\"39vA9YljbnOApXKniLWBZv\",\"name\":\"Sylvan Esso\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:39vA9YljbnOApXKniLWBZv\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"discNumber\":1,\"durationMs\":177920,\"externalIds\":{\"externalIds\":{\"isrc\":\"USC4R2003469\"}},\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/track/0yiJNNm39cp1T7RnXOMVLX\"}},\"href\":\"https://api.spotify.com/v1/tracks/0yiJNNm39cp1T7RnXOMVLX\",\"id\":\"0yiJNNm39cp1T7RnXOMVLX\",\"isPlayable\":null,\"linkedFrom\":null,\"restrictions\":null,\"name\":\"Ferris Wheel\",\"popularity\":52,\"previewUrl\":\"https://p.scdn.co/mp3-preview/3bfd901b251245306e9662617b8fb0f13b90f167?cid=ae02bde4d6ef4bc395502d8f76e38f04\",\"trackNumber\":3,\"type\":\"TRACK\",\"uri\":\"spotify:track:0yiJNNm39cp1T7RnXOMVLX\",\"isExplicit\":false},{\"album\":{\"albumGroup\":null,\"albumType\":\"ALBUM\",\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/2YZyLoL8N0Wb9xBt1NhZWg\"}},\"href\":\"https://api.spotify.com/v1/artists/2YZyLoL8N0Wb9xBt1NhZWg\",\"id\":\"2YZyLoL8N0Wb9xBt1NhZWg\",\"name\":\"Kendrick Lamar\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:2YZyLoL8N0Wb9xBt1NhZWg\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/album/79ONNoS4M9tfIA1mYLBYVX\"}},\"href\":\"https://api.spotify.com/v1/albums/79ONNoS4M9tfIA1mYLBYVX\",\"id\":\"79ONNoS4M9tfIA1mYLBYVX\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab67616d0000b2732e02117d76426a08ac7c174f\",\"width\":640},{\"height\":300,\"url\":\"https://i.scdn.co/image/ab67616d00001e022e02117d76426a08ac7c174f\",\"width\":300},{\"height\":64,\"url\":\"https://i.scdn.co/image/ab67616d000048512e02117d76426a08ac7c174f\",\"width\":64}],\"name\":\"Mr. Morale & The Big Steppers\",\"releaseDate\":\"2022-05-13\",\"releaseDatePrecision\":\"DAY\",\"restrictions\":null,\"type\":\"ALBUM\",\"uri\":\"spotify:album:79ONNoS4M9tfIA1mYLBYVX\"},\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/2YZyLoL8N0Wb9xBt1NhZWg\"}},\"href\":\"https://api.spotify.com/v1/artists/2YZyLoL8N0Wb9xBt1NhZWg\",\"id\":\"2YZyLoL8N0Wb9xBt1NhZWg\",\"name\":\"Kendrick Lamar\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:2YZyLoL8N0Wb9xBt1NhZWg\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"discNumber\":1,\"durationMs\":255377,\"externalIds\":{\"externalIds\":{\"isrc\":\"USUM72208961\"}},\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/track/5Gt9bxniM1SxN61yRzRhXL\"}},\"href\":\"https://api.spotify.com/v1/tracks/5Gt9bxniM1SxN61yRzRhXL\",\"id\":\"5Gt9bxniM1SxN61yRzRhXL\",\"isPlayable\":null,\"linkedFrom\":null,\"restrictions\":null,\"name\":\"United In Grief\",\"popularity\":79,\"previewUrl\":\"https://p.scdn.co/mp3-preview/08ce7a7c54f8f2473d06827284425343936416e1?cid=ae02bde4d6ef4bc395502d8f76e38f04\",\"trackNumber\":1,\"type\":\"TRACK\",\"uri\":\"spotify:track:5Gt9bxniM1SxN61yRzRhXL\",\"isExplicit\":true},{\"album\":{\"albumGroup\":null,\"albumType\":\"ALBUM\",\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/05lIUgmmsmTX2N9dCKc8rC\"}},\"href\":\"https://api.spotify.com/v1/artists/05lIUgmmsmTX2N9dCKc8rC\",\"id\":\"05lIUgmmsmTX2N9dCKc8rC\",\"name\":\"Rival Consoles\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:05lIUgmmsmTX2N9dCKc8rC\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/album/1BI2mpiBt99NlNvLka4QhG\"}},\"href\":\"https://api.spotify.com/v1/albums/1BI2mpiBt99NlNvLka4QhG\",\"id\":\"1BI2mpiBt99NlNvLka4QhG\",\"images\":[{\"height\":640,\"url\":\"https://i.scdn.co/image/ab67616d0000b2733bedd97f8cccbfa0f0028a42\",\"width\":640},{\"height\":300,\"url\":\"https://i.scdn.co/image/ab67616d00001e023bedd97f8cccbfa0f0028a42\",\"width\":300},{\"height\":64,\"url\":\"https://i.scdn.co/image/ab67616d000048513bedd97f8cccbfa0f0028a42\",\"width\":64}],\"name\":\"Persona\",\"releaseDate\":\"2018-04-13\",\"releaseDatePrecision\":\"DAY\",\"restrictions\":null,\"type\":\"ALBUM\",\"uri\":\"spotify:album:1BI2mpiBt99NlNvLka4QhG\"},\"artists\":[{\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/artist/05lIUgmmsmTX2N9dCKc8rC\"}},\"href\":\"https://api.spotify.com/v1/artists/05lIUgmmsmTX2N9dCKc8rC\",\"id\":\"05lIUgmmsmTX2N9dCKc8rC\",\"name\":\"Rival Consoles\",\"type\":\"ARTIST\",\"uri\":\"spotify:artist:05lIUgmmsmTX2N9dCKc8rC\"}],\"availableMarkets\":[\"AD\",\"AE\",\"AR\",\"AT\",\"AU\",\"BE\",\"BG\",\"BH\",\"BO\",\"BR\",\"CA\",\"CH\",\"CL\",\"CO\",\"CR\",\"CY\",\"CZ\",\"DE\",\"DK\",\"DO\",\"DZ\",\"EC\",\"EE\",\"EG\",\"ES\",\"FI\",\"FR\",\"GB\",\"GR\",\"GT\",\"HK\",\"HN\",\"HU\",\"ID\",\"IE\",\"IL\",\"IN\",\"IS\",\"IT\",\"JO\",\"JP\",\"KW\",\"LB\",\"LI\",\"LT\",\"LU\",\"LV\",\"MA\",\"MC\",\"MT\",\"MX\",\"MY\",\"NI\",\"NL\",\"NO\",\"NZ\",\"OM\",\"PA\",\"PE\",\"PH\",\"PL\",\"PS\",\"PT\",\"PY\",\"QA\",\"RO\",\"SA\",\"SE\",\"SG\",\"SK\",\"SV\",\"TH\",\"TN\",\"TR\",\"TW\",\"US\",\"UY\",\"VN\",\"ZA\"],\"discNumber\":1,\"durationMs\":99176,\"externalIds\":{\"externalIds\":{\"isrc\":\"GBWZD1810912\"}},\"externalUrls\":{\"externalUrls\":{\"spotify\":\"https://open.spotify.com/track/4G37OYV8HDnX01xdSyG2Ma\"}},\"href\":\"https://api.spotify.com/v1/tracks/4G37OYV8HDnX01xdSyG2Ma\",\"id\":\"4G37OYV8HDnX01xdSyG2Ma\",\"isPlayable\":null,\"linkedFrom\":null,\"restrictions\":null,\"name\":\"Fragment\",\"popularity\":51,\"previewUrl\":\"https://p.scdn.co/mp3-preview/475c806150ae79169388f36242fe1e1da35f4791?cid=ae02bde4d6ef4bc395502d8f76e38f04\",\"trackNumber\":12,\"type\":\"TRACK\",\"uri\":\"spotify:track:4G37OYV8HDnX01xdSyG2Ma\",\"isExplicit\":false}]";
        List<Track> tracks = TrackMapper.trackData(trackData);

        for(Track t : tracks) {
            System.out.println("Name: " + t.getName());
            System.out.println("Artist: " + t.getArtist());
            System.out.println("Album: " + t.getAlbumName());
            System.out.println("Cover: " + t.getImage());
            System.out.println("ID: " + t.getId());
            System.out.println("Preview: " + t.getPreviewUrl() + "\n");
        }

    }
}
