<script>
    import HostingHeader from "../../../../components/HostingHeader.svelte";
    import Card from "../../../../components/Card.svelte";
    import {onMount} from "svelte";
    import LocationForm from "../../../../components/LocationForm.svelte";
    import FloorPlanInputField from "../../../../components/FloorPlanInputField.svelte";
    import FilePond, { registerPlugin, supported } from 'svelte-filepond';
    import FilePondPluginImageExifOrientation from 'filepond-plugin-image-exif-orientation';
    import FilePondPluginImagePreview from 'filepond-plugin-image-preview';

    registerPlugin(FilePondPluginImageExifOrientation, FilePondPluginImagePreview);
    let pond;
    let name = 'filepond';



    let selectedPlaceCategoryCard;
    let selectedAmenities = [];

    const placeCategories = [
        { icon: '🏡', label: '주택' },
        { icon: '🏢', label: '아파트' },
        { icon: '🏚️', label: '헛간' },
        { icon: '🏨', label: '모텔' },
        { icon: '🚤', label: '보트' },
        { icon: '🏡', label: '통나무집' },
    ];

    const amenities = [
        {icon: '🛜', label: '무선 인터넷'},
        {icon: '📺', label: 'TV'},
        {icon: '🚘', label: '건물 내 무료 주차'},
        {icon: '❄️', label: '에어컨'},
        {icon: '🏋️', label: '운동 기구'},
        {icon: '️👕', label: '세탁 서비스'},
        {icon: '🏊', label: '수영장'},
        {icon: '🧯', label: '소화기'},
    ]

    function handleCardClick(amenityName) {
        if(selectedAmenities.includes(amenityName)) {
            selectedAmenities = selectedAmenities.filter(item => item !== amenityName);
        } else {
            selectedAmenities = [...selectedAmenities, amenityName];
        }
        console.log(selectedAmenities);
    }

    function placeCategoryClick(index) {
        selectedPlaceCategoryCard = index;
    }

    $: floorPlans = {
        "게스트": 4,
        "침실": 1,
        "침대": 1,
        "욕실": 1,
    }

    onMount(() => {
        const container = document.getElementById('map');
        const options = {
            center: new kakao.maps.LatLng(37.490823, 127.033435),
            level: 3
        };
        const map = new kakao.maps.Map(container, options);

        // 지도를 클릭한 위치에 표출할 마커입니다
        let marker = new kakao.maps.Marker({
            // 지도 중심좌표에 마커를 생성합니다
            position: map.getCenter()
        });
        // 지도에 마커를 표시합니다
        marker.setMap(map);

        // 지도에 클릭 이벤트를 등록합니다
        // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
        kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
            // 클릭한 위도, 경도 정보를 가져옵니다
            let latlng = mouseEvent.latLng;

            // 마커 위치를 클릭한 위치로 옮깁니다
            marker.setPosition(latlng);
            let message = '위도는 ' + latlng.getLat() + ' 이고, ';
            message += '경도는 ' + latlng.getLng() + ' 입니다';
            let resultDiv = document.getElementById('clickLatlng');
            resultDiv.innerHTML = message;})
    })
</script>

<HostingHeader />
<div class="flex flex-col p-10">
    <div class="mb-[10px] font-bold text-2xl">
        Q1. 당신의 숙소를 가장 잘 설명할 수 있는 카테고리는 무엇인가요?
    </div>
    <div class="grid grid-cols-3 gap-4 justify-center">
        {#each placeCategories as placeCategory, index}
            <Card
                    icon={placeCategory.icon}
                    label={placeCategory.label}
                    isActive={selectedPlaceCategoryCard === index}
                    onClick={() => placeCategoryClick(index)}
            />
        {/each}
    </div>

    <hr class="my-[20px]"/>

    <div class="mb-[10px] flex flex-col">
        <span class="mb-5 font-bold text-2xl">Q2. 숙소 위치를 입력해주세요</span>
        <div class="flex flex-col justify-center items-center">
            <div id="map" class="w-[500px] h-[400px]"></div>
            <div id="clickLatlng" class="mt-4" ></div>
        </div>
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px]">
        <span class="mb-4 font-bold text-2xl">Q3. 숙소 상세 주소를 확인해주세요</span>
        <LocationForm />
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q4. 숙소 기본 정보를 알려주세요</span>
        <span class="mb-4">침대 유형과 같은 세부 사항은 나중에 추가하실 수 있습니다</span>
        <div class="border-none max-w-md mt-10 p-2">
            {#each Object.entries(floorPlans) as [title, numOfCategory]}
                <FloorPlanInputField {title} bind:numOfCategory={numOfCategory}/>
            {/each}
        </div>
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q5. 숙소 편의시설 정보를 추가하세요</span>
        <span>여기에 추가하려는 편의시설이 보이지 않더라도 걱정하지 마세요!</span>
        <span>숙소를 등록한 후에 편의시설을 추가할 수 있습니다.</span>
        <div class="grid grid-cols-3 gap-4 justify-center pt-5">
            {#each amenities as amenity}
                <Card
                        icon={amenity.icon}
                        label={amenity.label}
                        isActive={selectedAmenities.includes(amenity.label)}
                        onClick={() => handleCardClick(amenity.label)}
                />
            {/each}
        </div>
    </div>


    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q6. 사진 추가하기</span>
        <div class="w-[1000px] aspect-video">
            <FilePond
                    bind:this={pond}
                    {name}
                    allowMultiple={true}
            />
        </div>
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q7. 이제 주택에 이름을 지어주세요</span>
        <span class="mb-4">숙소 이름은 짧을수록 효과적입니다. 나중에 언제든지 변경할 수 있으니, 너무 걱정하지 마세요.(35자 제한)</span>
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q8. 이제 주택에 이름을 지어주세요</span>
        <span class="mb-4">숙소 설명 작성하기 (기본 value - 편안함을 자랑하는 이곳에서 즐거운 시간을 보내실 수 있을 것입니다. 500자 제한)</span>
    </div>

    <hr class="my-[20px]"/>
    <div class="my-[10px] flex flex-col">
        <span class="mb-4 font-bold text-2xl">Q9. 이제 요금을 설정하세요</span>
        <span class="mb-4">언제든지 변경하실 수 있습니다. (₩13,659에서 ₩13,658,393)</span>
    </div>
</div>
