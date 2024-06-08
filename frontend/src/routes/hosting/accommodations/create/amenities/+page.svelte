<script>
    import QuestionSection from "../QuestionSection.svelte";
    import RoutingBtn from "../../../../../components/RoutingBtn.svelte";
    import {urlPrefix} from "../Constants.js";
    import Card from "../../../../../components/Card.svelte";

    let selectedAmenities = [];

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
</script>

<QuestionSection title="Q8. 숙소 편의시설 정보를 추가하세요">
    <span>여기에 추가하려는 편의시설이 보이지 않더라도 걱정하지 마세요!</span>
    <span>숙소를 등록한 후에 편의시설을 추가할 수 있습니다.</span>
    <div class="grid grid-cols-3 gap-4 justify-center pt-5 w-[1440px]">
        {#each amenities as amenity}
            <Card
                    icon={amenity.icon}
                    label={amenity.label}
                    isActive={selectedAmenities.includes(amenity.label)}
                    onClick={() => handleCardClick(amenity.label)}
            />
        {/each}
    </div>
</QuestionSection>

<div class="flex mt-5 w-[75%] justify-between">
    <RoutingBtn targetUrl="{urlPrefix}/floorPlan" btnText="이전"/>
    <RoutingBtn targetUrl="{urlPrefix}/images" btnText="다음"/>
</div>