<script>
    export let location;
    export let title;
    export let floorPlan;
    export let amenities = [];
    export let pricePerNight;
    export let totalPrice;
</script>

<div class="w-[685px] h-[260px] flex border-b-airbnb-text-normal border-gray-200 shadow m-0.5 hover:cursor-pointer">
    <img src="https://images.contentstack.io/v3/assets/bltec2ed8e3c4b1e16d/bltfbcc7f32e0cd6ff5/getting-started-on-airbnb-optimized.jpg"
         class="object-cover w-[320px] h-full p-3 rounded-[1.5rem]"
         alt="이미지~">
    <div class="flex flex-col m-4 w-full">
        <div class="flex-grow">
            <div>
                <p class="text-gray-500 text-sm mb-3">{location}</p>
            </div>
            <div>
                <p class="mb-2.5 text-xl font-semibold text-gray-900">{title}</p>
            </div>
            <div class="text-gray-500 text-sm mb-2.5 flex flex-col gap-2">
                <div class="flex gap-2">
                    <p>최대 인원 {floorPlan.maxGuestCount}명  /</p>
                    <p> 방 {floorPlan.roomCount}개  /</p>
                    <p> 침대 {floorPlan.bedCount}개  /</p>
                    <p> 욕실 {floorPlan.bathroomCount}개</p>
                </div>
                <div class="flex gap-2">
                    {#each amenities as amenity}
                        <p>{amenity}</p>
                    {/each}
                </div>
            </div>
        </div>
        <div class="flex flex-col">
            <div class="flex gap-1 justify-end">
                <p>₩{pricePerNight}</p><p>/ 박</p>
            </div>
            <div class="flex justify-end text-sm text-gray-500 underline">
                <p>총액 ₩{totalPrice}</p>
            </div>
        </div>
    </div>
</div>